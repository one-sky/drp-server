package com.drp.repository.impl;

import com.drp.Util.Delete;
import com.drp.Util.Insert;
import com.drp.Util.Update;
import com.drp.entity.*;
import com.drp.repository.ProductRepository;
import com.drp.vo.*;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public List<SpuSearchVO> getProductList(SpuSearchVO vo) {

        Session session = this.getCurrentSession();

        String sql = "select m.*,CONCAT(n.lowPrice,'~',n.highPrice) as priceRange from (\n" +
                "select a.id as productId, product_name as productName, a.brand_id as brandId, a.thumbnailImage, a.online,  \n" +
                " brand_name as brandName, a.category_id as categoryId, min(c.retail_price) as retailPrice, cast(sum(sales_num) as char(11)) as salesNum \n" +
                " from p_product_category as a join r_brand as b on a.brand_id = b.id join p_product_sku as c on a.id = c.product_id \n" +
                " group by a.id , product_name, a.brand_id, brand_name, a.category_id) as m join (\n" +
                "select product_id, min(lowPrice) as lowPrice, max(highPrice) as highPrice \n" +
                "from (select p.sku_id, q.product_id, min(p.price) as lowPrice, max(p.price) as highPrice \n" +
                "from p_product_quantity as p join p_product_sku as q on p.sku_id = q.id group by p.sku_id) as t \n" +
                "group by t.product_id) as n on m.productId = n.product_id ";

        String condition = "";
        if (!StringUtils.isEmpty(vo.getProductName())) {

            condition = condition + "productName like %" + vo.getProductName() + "%)";

        }
        if (!StringUtils.isEmpty(vo.getOnline())) {

            condition = condition + "online ='" + vo.getOnline() + "'";

        }
        if (vo.getCategoryIds() != null && !vo.getCategoryIds().isEmpty()) {
            List<Integer> categoryIds=vo.getCategoryIds();
            if (!StringUtils.isEmpty(condition)) {
                condition = condition + " AND " ;
            }
            String tmp="(";
            List<Integer> productIds=vo.getProductIds();
            for(Integer category:categoryIds) {

                tmp += category+", ";

            }
            int length=tmp.length();
            if(length>1){

                tmp="categoryId in "+tmp.substring(0,length-2)+")";
                condition += tmp;
            }else{
                //报错
            }

        }


        if(null!=vo.getProductIds() && !vo.getProductIds().isEmpty()){
            if (!StringUtils.isEmpty(condition)) {
                condition = condition + " AND " ;
            }
            String tmp="(";
            List<Integer> productIds=vo.getProductIds();
            for(Integer productId:productIds) {

                tmp += productId+", ";

            }
            int length=tmp.length();
            if(length>1){

                tmp="productId in "+tmp.substring(0,length-2)+")";
                condition += tmp;
            }else{
                //报错
            }
        }

        if(null!=vo.getBrandIds() && !vo.getBrandIds().isEmpty()){
            if (!StringUtils.isEmpty(condition)) {
                condition = condition + " AND " ;
            }
            String tmp="(";
            List<Integer> brandIds=vo.getBrandIds();
            for(Integer brandId:brandIds) {

                tmp += brandId+", ";

            }
            int length=tmp.length();
            if(length>1){

                tmp="brandId in "+tmp.substring(0,length-2)+")";
                condition += tmp;
            }else{
                //报错
            }
        }

        if (!StringUtils.isEmpty(vo.getBrandName())) {
            if (!StringUtils.isEmpty(condition)) {
                condition = condition + " AND " ;
            }

            condition = condition + "(brandName:" + vo.getBrandName() + ")";
        }
        if (!StringUtils.isEmpty(condition)) {
            sql += "where "+condition;
        }

        // 排序
        String orderStr="productId";
        String clause="asc";
        if(null!=vo.getOrderField()){

            orderStr=vo.getOrderField();
            if(null!=vo.getOrderDirection()){
                clause=(vo.getOrderDirection().equals(1)?"desc":"asc");
            }else{
                clause=" asc";
            }
        }

        sql += " order by " + orderStr + " " + clause;

        SQLQuery sqlQuery=session.createSQLQuery(sql);
        sqlQuery.setFirstResult(vo.getStartIndex());
        sqlQuery.setMaxResults(vo.getPageSize()).setResultTransformer(Transformers.aliasToBean(SpuSearchVO.class));
        List<SpuSearchVO> list=sqlQuery.list();
        return list;

    }

    public List<SkuPriceDetailVO> getPriceListPromotion(Integer spuId, Integer skuId) {
        Session session = this.getCurrentSession();

        String sql = "select a.sku_id as skuId, a.promotion_id as promotionId, \n" +
                "a.low_quantity as startPiece, a.high_quantity as endPiece, a.price, a.promotion_stock as stock \n" +
                "from p_promote_product as a \n" +
                "join p_product_sku as b on a.sku_id = b.id\n" +
                "join p_product_promotion as c on c.id = a.promotion_id where  now() between c.sales_eff_start and c.sales_eff_end ";
        String condition = "";
        if (null!=spuId && 0!=spuId) {
            condition = " and b.product_id = " + spuId;
        }
        if (null!=skuId && 0!=skuId) {
            condition += " and a.sku_id = " + skuId;
        }
        sql +=condition + " order by a.sku_id, startPiece";
        SQLQuery sqlQuery=session.createSQLQuery(sql);
        List<SkuPriceDetailVO> list=sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list();
        return list;
    }

    public List<SkuPriceDetailVO> getPriceList(List<Integer> skuIdList, Integer distributorId) {
        Session session = this.getCurrentSession();

        String sql = "select a.id as skuId, b.low_quantity as startPiece, b.high_quantity as endPiece, \n" +
                "b.level as levelId, b.price, a.stock \n" +
                "from p_product_sku as a join p_product_quantity as b on a.id = b.sku_id ";

        String condition = "";
        if (null!=distributorId && 0!=distributorId) {
            condition = condition + "where b.level = (select vip_id from d_distributor where id =" + distributorId + ") ";
        }
        if(null!=skuIdList && !skuIdList.isEmpty()){
            String tmp="(";
            for(Integer skuId:skuIdList) {

                tmp += skuId+", ";

            }
            int length=tmp.length();
            if(length>1){

                tmp="a.id in "+tmp.substring(0,length-2)+")";
                condition += " and "+tmp;
            }else{
                //报错
            }
        }
        sql += condition + "order by a.id, startPiece";

        SQLQuery sqlQuery=session.createSQLQuery(sql);
        List<SkuPriceDetailVO> list=sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list();
        return list;
    }

    public List<SkuPriceDetailVO> getPriceListByDistributorId(List<Integer> skuIdList, Integer distributorId) {
        Session session = this.getCurrentSession();

        String sql = "select sku_id as skuId, low_quantity as startPiece, high_quantity as endPiece, price, stock, " +
                " 'Y' as special from p_skuprice_distributor where distributor_id = " +
                distributorId + " and sku_id in ";

        String tmp="(";
        if(null!=skuIdList && !skuIdList.isEmpty()){

            for(Integer skuId:skuIdList) {

                tmp += skuId+", ";

            }
            int length=tmp.length();
            if(length>1){

                tmp = tmp.substring(0,length-2)+")";
            }else{
                //报错
            }
        }
        sql += tmp + "order by sku_id, startPiece";

        SQLQuery sqlQuery=session.createSQLQuery(sql);
        List<SkuPriceDetailVO> list=sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list();
        return list;
    }

    public List<PProductArticleEntity> getArticleList(Integer spuId, Integer distributorId) {
        Criteria c = getCurrentSession().createCriteria(PProductArticleEntity.class);
        c.add(Restrictions.eq("spuId", spuId));
        if(null!=distributorId && 0 !=distributorId) {
            c.add(Restrictions.eq("status", 1));
        }
        c.addOrder( Order.asc("sortBy")).addOrder( Order.desc("lastUpdateBy"));
        return c.list();
    }

    public List<CollectProductVO> getCollectionProductList(Integer distributorId, Integer pageSize, Integer startIndex) {
        Session session = this.getCurrentSession();
        String sqlString="SELECT a.id, a.distributor_id as distributorId, a.sort_by as sortBy, c.*,\n" +
                "b.product_name as spuName, cast(a.price as nchar) as price, b.thumbnailImage, b.online\n" +
                " FROM d_collection as a join p_product_category as b on a.product_id = b.id join (\n" +
                " select product_id as spuId, cast(min(retail_price)as nchar) as retailPrice, cast(sum(stock) as nchar) as stock from p_product_sku group by spuId ) \n" +
                " as c on b.id = c.spuId\n" +
                " where a.distributor_id = " + distributorId + " order by a.sort_by, a.last_update_time";
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        sqlQuery.setFirstResult(startIndex);
        sqlQuery.setMaxResults(pageSize);
        List<CollectProductVO> list=sqlQuery.setResultTransformer(Transformers.aliasToBean(CollectProductVO.class)).list();;
        return list;
    }

    public Integer cancelCollectionProduct(Integer distributorId, List<Integer> ids) {
        Session session = this.getCurrentSession();
        String sqlString="delete from d_collection";
        String tmp="";
        if(null!=ids && !ids.isEmpty()){
            for(Integer id:ids) {

                tmp += id+", ";

            }
            int length=tmp.length();
            if(length>0){

                tmp = tmp.substring(0,length-2);
            }else{
                //报错
            }
            sqlString += " where id in (" + tmp + ")";
        }


        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        return sqlQuery.executeUpdate();
    }

    public Integer addCollectionProduct(Integer distributorId, List<Integer> spuIds , DCollectionEntity entity) {
        Integer data = -1;
        Session session = this.getCurrentSession();
        DCollectionEntity collection;
        if(null!=spuIds && !spuIds.isEmpty()){
            String sqlString="SELECT product_id as spuId, cast(min(retail_price) as nchar) as price \n" +
                    "FROM drp.p_product_sku";
            String tmp="";
            for(Integer id:spuIds) {
                tmp += id+", ";
            }
            int length=tmp.length();
            if(length>0){
                tmp = tmp.substring(0,length-2);
            }else{
                //报错
            }
            sqlString += " where product_id in (" + tmp + ") group by product_id";
            SQLQuery sqlQuery=session.createSQLQuery(sqlString);
            List<CollectProductVO> list=sqlQuery.setResultTransformer(Transformers.aliasToBean(CollectProductVO.class)).list();;
            Transaction tx = null;
            try {
                // 开始事务
                tx = session.beginTransaction();
                for(int i = 0; i < spuIds.size(); i++) {
                    CollectProductVO result = list.get(i);
                    // 持久化
                    entity.setProductId(result.getSpuId());
                    entity.setPrice(new BigDecimal(result.getPrice()));
                    session.save(entity);
                    // 批插入的对象立即写入数据库并释放内存
                    if (i!=0 && i % 10 == 0) {
                        tx.commit();
                        session.flush();
                        session.clear();
                    }
                }
                tx.commit();
                data = 1;
                // 提交事务
            }catch (Exception e) {
                if( tx != null) {
                    // 事务回滚
                    tx.rollback();
                }
            }finally {
                session.close();
            }

        }
        return data;
    }

    public List<List<SkuPriceDetailVO>> searchPrice(List<Integer> skuIdList, Integer distributorId) {
        Session session = this.getCurrentSession();
        List<List<SkuPriceDetailVO>> data = new ArrayList<List<SkuPriceDetailVO>>();
        // 获取原价
        String sqlString="select a.sku_id as skuId, a.price, 'N' as special from p_product_quantity as a " +
                "where low_quantity = 1 and level = " +
                "(select min(level) from p_product_quantity as b where a.sku_id = b.sku_id group by b.sku_id)";
        String tmp="";
        for(Integer id:skuIdList) {

            tmp += id+", ";

        }
        int length=tmp.length();
        if(length>0){

            tmp = tmp.substring(0,length-2);
        }else{
            //报错
        }
        sqlString += " and a.sku_id in (" + tmp + ") order by a.sku_id";

        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        data.add(sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list());

        // 获取特殊的价格
        sqlString="select sku_id as skuId, low_quantity as startPiece, high_quantity as endPiece, price, stock, 'Y' as special " +
                "from p_skuprice_distributor where sku_id in (" + tmp + ") and distributor_id = " + distributorId + " order by sku_id";
        sqlQuery=session.createSQLQuery(sqlString);
        data.add(sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list());

        // 获取促销的价格
        sqlString="select sku_id as skuId, promotion_id as promotionId, low_quantity as startPiece, " +
                "high_quantity as endPiece, price, promotion_stock as stock, 'N' as special " +
                "from p_promote_product where sku_id in (" + tmp + ") order by sku_id";
        sqlQuery=session.createSQLQuery(sqlString);
        data.add(sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list());

        // 获取该分销商会员等级下的价格
        sqlString="select a.sku_id as skuId, a.level as levelId, a.low_quantity as startPiece, " +
                "a.high_quantity as endPiece, a.price, b.stock, 'N' as special " +
                "from p_product_quantity as a join p_product_sku as b on a.sku_id = b.id " +
                "where level = (select vip_id from d_distributor where id = " + distributorId + ") and a.sku_id in (" + tmp + ")" +
                " order by a.sku_id";
        sqlQuery=session.createSQLQuery(sqlString);
        data.add(sqlQuery.setResultTransformer(Transformers.aliasToBean(SkuPriceDetailVO.class)).list());

        return data;

    }

    public List<ShoppingCartItemVO> getBasicShoppingCartList(Integer distributorId, Integer type) {
        Session session = this.getCurrentSession();
        List<ShoppingCartItemVO> data;
        String sqlString ="select a.id, a.sku_id as skuId, b.product_id as spuId, a.quantity, c.online as status,\n" +
                " a.type, c.brand_id as brandId, b.sku_code as skuCode, cast(a.price as nchar) as price , c.product_name as spuName,\n" +
                " a.last_update_time as updateTime, b.sku_attr as skuAttr,  b.sku_img as skuImg\n" +
                " from p_cart as a join p_product_sku as b on a.sku_id = b.id join p_product_category as c on b.product_id = c.id " +
                "where a.distributor_id = " + distributorId;
        if(null != type && 0!=type) {
            sqlString += " and type = '" + type + "'";
        }
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        data = sqlQuery.setResultTransformer(Transformers.aliasToBean(ShoppingCartItemVO.class)).list();
        return data;
    }

    public Integer updateShoppingCart(PCartEntity cart) {
        Session session = this.getCurrentSession();
        return new Update<PCartEntity>(session, cart).getData();

    }

    public Integer insertShoppingCart(PCartEntity cart) {
        Session session = this.getCurrentSession();
        return new Insert<PCartEntity>(session, cart).getData();

    }

    public Integer deleteShoppingCart(PCartEntity cart) {
        Session session = this.getCurrentSession();
        return new Delete(session, cart).getData();
    }

    public Integer updateSkuLockStock(List skuList) {

        Session session = this.getCurrentSession();
        Transaction tx = null;
        String sqlString = "";
        try {

            // 开始事务
            tx = session.beginTransaction();
            String dbName = "";
            int changeStock;
            // 持久化
            for( int i = 0; i<skuList.size(); i++) {

                // 特殊分销商
                String name = skuList.get(i).getClass().getName();
                if(name.equals("com.drp.entity.PSkupriceDistributorEntity")) {
                    PSkupriceDistributorEntity entity = (PSkupriceDistributorEntity) skuList.get(i);
                    dbName = "p_skuprice_distributor";
                    changeStock = entity.getChangeStock();
                    int skuId = entity.getSkuId();
                    int distributorId = entity.getDistributorId();
                    sqlString = "update " + dbName + " set " +
                            "stock = (select stock from (select stock +(" + changeStock + ") as stock from " + dbName + " where sku_id = " + skuId +
                            " and distributor_id = " + distributorId + " ) as a)," +
                            " lockStock = (select lockStock from (select lockStock - (" + changeStock + ") as lockStock from " + dbName +" where sku_id = " + skuId +
                            " and distributor_id = "+ distributorId +  ") as b) where  sku_id = " + skuId +
                            " and distributor_id = " + distributorId;
                } else if(name.equals("com.drp.entity.PPromoteProductEntity")) {
                    PPromoteProductEntity entity = (PPromoteProductEntity) skuList.get(i);
                    dbName = "p_promote_product";
                    changeStock = entity.getChangeStock();
                    int skuId = entity.getSkuId();
                    int promotionId = entity.getPromotionId();
                    int lowQuantity = entity.getLowQuantity();
                    sqlString = "update " + dbName + " set " +
                            " promotion_stock = (select stock from (select promotion_stock +(" + changeStock + ") as stock from " + dbName + " where sku_id = " + skuId +
                            " and promotion_id = " + promotionId + " and low_quantity = " + lowQuantity + " ) as a), " +
                            " lockStock = (select lockStock from (select lockStock - (" + changeStock + ") as lockStock from " + dbName +" where sku_id = " + skuId +
                            " and promotion_id = " + promotionId + " and low_quantity = " + lowQuantity + " ) as b) where sku_id = " + skuId +
                            " and promotion_id = " + promotionId + " and low_quantity = " + lowQuantity;
                } else {
                    PProductSkuEntity entity = (PProductSkuEntity) skuList.get(i);
                    dbName = "p_product_sku";
                    changeStock = entity.getChangeStock();
                    int id = entity.getId();
                    sqlString = "update " + dbName + " set stock = select stock from (select stock +(" + changeStock + ") as stock from " + dbName + " where id = " + id +
                            " ) as a)," +
                            " lockStock = (select lockStock from (select lockStock - (" + changeStock + ") as lockStock from " + dbName + " where id = " + id +
                            ") as b) where id = " + id;
                }

                SQLQuery sqlQuery=session.createSQLQuery(sqlString);
                sqlQuery.executeUpdate();
            }

            // 提交事务
            tx.commit();
            return 1;
        }catch (Exception e) {
            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
            return -1;
        }finally {
            session.close();
        }

    }
}
