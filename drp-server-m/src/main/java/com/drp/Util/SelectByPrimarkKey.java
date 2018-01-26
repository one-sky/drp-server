package com.drp.Util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class SelectByPrimarkKey<T> {

    private T data;

    private Integer id;

    private Session session;

    public SelectByPrimarkKey(String name, Session session, Integer id) {

        Transaction tx = null;
        this.id = id;
        this.session = session;
        try {
            // 开始事务
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + name + " as a where a.id = "+ id);
            T entity = (T)query.uniqueResult();
            // 提交事务
            tx.commit();
            this.data = entity;
        }catch (Exception e) {
            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
        }finally {
            session.close();
        }

    }

    public T getData() {
        return data;
    }

    public void setData(T entity) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
