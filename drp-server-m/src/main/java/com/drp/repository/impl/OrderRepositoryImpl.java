package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.OOrderEntity;
import com.drp.entity.OOrderItemEntity;
import com.drp.repository.OrderRepository;
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

    public OrderVO getOrderDetailByOrderCode(String orderCode) {
        Session session = this.getCurrentSession();
        OrderVO vo = new OrderVO();
        String sql = "select * from o_order where orderCode = '" + orderCode + "'";

        SQLQuery sqlQuery=session.createSQLQuery(sql);
        List<OrderVO> result = sqlQuery.addEntity(OrderVO.class).list();
        // List<OrderVO> result = sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderVO.class)).list();
        if(!result.isEmpty()) {
            vo = result.get(0);
        }

        return vo;
    }
}
