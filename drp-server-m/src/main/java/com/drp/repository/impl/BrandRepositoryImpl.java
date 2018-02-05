package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.Update;
import com.drp.entity.DAgentBrandEntity;
import com.drp.entity.RBrandAttachmentEntity;
import com.drp.entity.RBrandEntity;
import com.drp.repository.BrandRepository;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl implements BrandRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }


    public List<RBrandEntity> getBrandListByAgentBrand(Integer distributorId, String brandName,
                                                       String status, Integer pageSize, Integer startIndex) {

        if(brandName == null) {
            brandName = new String();
        }
        Session session = this.getCurrentSession();
        String sqlString="select * from r_Brand where is_finished = 'Y' and brand_name like '%"+brandName +"%'";
        if(distributorId != 0 && status != null && !"".equals(status)) {
            sqlString += " and id in (select brand_id from d_agent_brand where status = '"
                    + status + "' and distributor_id = " + distributorId + ")";
        }
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        sqlQuery.addEntity(RBrandEntity.class);
        sqlQuery.setFirstResult(startIndex);
        sqlQuery.setMaxResults(pageSize);
        List<RBrandEntity> list=sqlQuery.list();
        return list;
    }

    public List<RBrandEntity> getBrandListByCategoryId(Integer categoryId) {
        Criteria c = getCurrentSession().createCriteria(RBrandEntity.class);
        if(null != categoryId && categoryId > 0 ) {
            c.add(Restrictions.eq("categoryId", categoryId));
            c.setFirstResult(0);
            c.setMaxResults(5);
        }
        c.add(Restrictions.eq("isFinished", "Y"))
                .addOrder( Order.desc("createTime"));
        return c.list();
    }

    public DAgentBrandEntity getAgentBrand(Integer distributorId, Integer brandId) {
        Criteria c = getCurrentSession().createCriteria(DAgentBrandEntity.class);
        c.add(Restrictions.eq("distributorId", distributorId));
        c.add(Restrictions.eq("brandId", brandId));
        c.addOrder(Order.desc("lastUpdateTime"));
        List<DAgentBrandEntity> result = c.list();
        if(!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    /**
     * 获取最新品牌，只获取前num条记录
     * @param
     * @return
     */
    public List<RBrandEntity> getNewBrandList(Integer num) {

        Criteria c = getCurrentSession().createCriteria(RBrandEntity.class);
        c.setFirstResult(0);
        c.setMaxResults(num);
        c.add(Restrictions.eq("isFinished", "Y"))
                .addOrder( Order.desc("createTime"));
        return c.list();

    }

    public RBrandEntity getBrandById(Integer id) {

        Criteria c = getCurrentSession().createCriteria(RBrandEntity.class);
        c.add(Restrictions.eq("id", id));
        RBrandEntity result = (RBrandEntity)c.list().get(0);
        return result;

    }

    public List<RBrandAttachmentEntity> getBrandAttachment(Integer brandId) {
        Criteria c = getCurrentSession().createCriteria(RBrandAttachmentEntity.class);
        c.add(Restrictions.eq("brandId", brandId));
        return c.list();
    }

    public Integer insertAgentBrand(DAgentBrandEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<DAgentBrandEntity>(session, entity).getData();
    }

    public Integer updateAgentBrand(DAgentBrandEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<DAgentBrandEntity>(session, entity).getData();
    }

    public String isAgentBrand(Integer distributorId, Integer skuId) {
        Session session = this.getCurrentSession();
        String sqlString="SELECT status FROM d_agent_brand where brand_id = (\n" +
                "select brand_id from p_product_category \n" +
                "where id = ( select product_id from p_product_sku where id = " + skuId +")) and distributor_id = " + distributorId + "";
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        if(sqlQuery.list().isEmpty()) {
            return "N";
        } else {
            return sqlQuery.list().get(0).toString();
        }
    }
}
