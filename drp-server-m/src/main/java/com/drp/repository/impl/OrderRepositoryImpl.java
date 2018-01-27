package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.OOrderEntity;
import com.drp.entity.OOrderItemEntity;
import com.drp.repository.OrderRepository;
import com.drp.vo.OrderItemVO;
import com.drp.vo.OrderSearchVO;
import com.drp.vo.OrderVO;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public Integer insertOrder(OOrderEntity order) {
        Session session = this.getCurrentSession();
        return new Insert<OOrderEntity>(session, order).getData();
    }


    public Integer insertOrderItemList(List<OOrderItemEntity> orderItemVOList, Integer orderId){
        Session session = this.getCurrentSession();
        Transaction tx = null;
        try {
            // 开始事务
            tx = session.beginTransaction();
            for(OOrderItemEntity entity : orderItemVOList) {
                entity.setOrderId(orderId);
                // 持久化
                session.save(entity);
            }
            // 提交事务
            tx.commit();
        }catch (Exception e) {

            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return 1;

    }

    public Integer payOrder(OOrderEntity order) {
        Session session = this.getCurrentSession();
        return new Update<OOrderEntity>(session, order).getData();
    }

    public List<OOrderEntity> searchOrderById(List<Integer> ids) {
        Session session = this.getCurrentSession();
        Transaction tx = null;
        List<OOrderEntity> result = new ArrayList<OOrderEntity>();
        try {
            // 开始事务
            tx = session.beginTransaction();

            for(Integer id : ids) {

                Query query = session.createQuery("from OOrderEntity as a where a.id = "+ id);
                OOrderEntity entity = (OOrderEntity) query.uniqueResult();
                // 持久化
                session.save(entity);
                result.add(entity);
            }
            // 提交事务
            tx.commit();

        }catch (Exception e) {

            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return result;
    }

    public String cancelOrderList(List<OOrderEntity> orderList) {
        Session session = this.getCurrentSession();
        Transaction tx = null;
        List<OOrderEntity> result = new ArrayList<OOrderEntity>();
        try {
            // 开始事务
            tx = session.beginTransaction();
            for(OOrderEntity entity : orderList) {
                // 持久化
                session.update(entity);
            }
            // 提交事务
            tx.commit();
            return "成功";
        }catch (Exception e) {
            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
            return "失败";
        }finally {
            session.close();
        }

    }

    public List<OrderVO> getOrderList(OrderSearchVO vo) {
        Session session = this.getCurrentSession();
        String sql = "select id, order_code as orderCode, distributor_id as distributorId, account_id as accountId, \n" +
                "type, total_amount as totalAmount, paid_amount as paidAmount, status, shipping_cost as shippingCost,\n" +
                " trancation_id as trancationId,  payment_status as paymentStatus, payment_channel as paymentChannel,\n" +
                " payment_time as paymentTime, order_item_quantity as orderItemQuantity,  item_subtoal as itemSubtoal,  \n" +
                " order_time as orderTime,  cancel_type as  cancelType, cancel_reason as cancelReason, cancel_time as cancelTime,\n" +
                " buyer_message as buyerMessage, delivery_time as deliveryTime, finish_time as finishTime, create_by as createBy,\n" +
                " create_time as createTime, last_update_by as lastUpdateBy, last_update_time as lastUpdateTime,\n" +
                " receiver_name as receiveName, receiver_phone as receivePhone,  province, city, area, detail_address as detailAddress " +
                "from o_order";
        sql += " where distributor_id = " + vo.getDistributorId();

        if(null!=vo.getOrderIds() && !vo.getOrderIds().isEmpty()) {
            String tmp="(";
            for(Integer orderId:vo.getOrderIds()) {

                tmp += orderId+", ";

            }
            int length=tmp.length();
            if(length>1){
                sql += " and id in "+tmp.substring(0,length-2)+")";
            }else{
                //报错
            }
        }
        if (null != vo.getOrderCode() && !"".equals(vo.getOrderCode())) {
            sql += " and order_code like '%" + vo.getOrderCode() + "%' ";
        }
        if (null != vo.getStartDate() && !"".equals(vo.getStartDate())) {
            sql += " and order_time &gt;= DATE_FORMAT(" + vo.getStartDate() + ", %Y-%m-%d %H:%i:%s) ";
        }
        if (null != vo.getEndDate() && !"".equals(vo.getEndDate())) {
            sql += " and order_time &lt;= DATE_FORMAT(" + vo.getEndDate() + ", %Y-%m-%d %H:%i:%s) ";
        }
        if (null != vo.getOrderStatus() && 0 != vo.getOrderStatus()) {
            sql += " and status = " + vo.getOrderStatus();
        }
        if (null != vo.getPayStatus() && 0 != vo.getPayStatus()) {
            sql += " and payment_status = " + vo.getPayStatus();
        }
        if (null != vo.getReceiveName() && !"".equals(vo.getReceiveName())) {
            sql += " and receiver_name like '%" + vo.getReceiveName() + "%'";
        }
        if (null != vo.getReceivePhone() && !"".equals(vo.getReceivePhone())) {
            sql += " and receiver_phone like '%" + vo.getReceivePhone() + "%'";
        }
        sql += " order by order_time desc";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setFirstResult(vo.getStartIndex());
        sqlQuery.setMaxResults(vo.getPageSize());
        List<OrderVO> result = sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderVO.class)).list();
        return result;
    }

    public List<OrderItemVO> getOrderDetail(OrderSearchVO vo) {
        Session session = this.getCurrentSession();
        String sql = "select a.id,  a.order_id as orderId, a.sku_id as skuId, b.sku_attr as skuAttr, \n" +
                "b.product_id as spuId, c.product_name as spuName, a.orginal_price as originalPrice, \n" +
                "a.sku_price as skuPrice, a.sku_quantity as skuQuantity, b.sku_img as skuImg, a.status, a.last_update_time as lastUpdateTime \n" +
                "from o_order_item as a join p_product_sku as b on a.sku_id = b.id join p_product_category as c on b.product_id = c.id";
        String condition = "";
        if (null!=vo.getOrderCode() && !"".equals(vo.getOrderCode())) {
            condition = " where a.order_id in (select order_id from o_order where order_code like '%" + vo.getOrderCode() + "%')";

        } else {
            if(null!=vo.getOrderIds() && !vo.getOrderIds().isEmpty()) {
                String tmp="(";
                for(Integer orderId:vo.getOrderIds()) {

                    tmp += orderId+", ";

                }
                int length=tmp.length();
                if(length>1){

                    tmp="order_id in "+tmp.substring(0,length-2)+")";
                    condition += " where "+tmp;
                }else{
                    //报错
                }
            }
        }

        if (null!=vo.getItemName() && !"".equals(vo.getItemName())) {
            condition += " and c.product_name like '%" + vo.getItemName() + "%'";
        }
        sql += condition + "order by a.order_id, a.last_update_time, a.id";

        SQLQuery sqlQuery=session.createSQLQuery(sql);
        List<OrderItemVO> result = sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderItemVO.class)).list();

        return result;
    }
}
