package com.drp.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class Update<T> {
    Integer data;

    T entity;

    private Session session;

    public Update(Session session, T entity) {

        this.entity = entity;
        this.session = session;

        Transaction tx = null;
        try {
            // 开始事务
            tx = session.beginTransaction();
            // 持久化
            session.update(entity);
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

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
