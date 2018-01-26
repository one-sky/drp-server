package com.drp.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Delete<T> {

    private Integer data;

    private T entity;

    private Session session;



    public Delete(Session session, T entity) {
        this.entity = entity;
        this.session = session;
        Transaction tx = null;

        try {
            // 开始事务
            tx = session.beginTransaction();
            // 持久化
            session.delete(entity);
            // 提交事务
            tx.commit();
            this.data = 1;
        }catch (Exception e) {

            this.data = 0;
            if( tx != null) {
                // 事务回滚
                tx.rollback();
            }
        }finally {
            session.close();
        }

    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
