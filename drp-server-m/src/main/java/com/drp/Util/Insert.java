package com.drp.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Insert<T> {

    private Integer data;

    private T entity;

    private Session session;

    public Insert(Session session, T entity ) {

        this.entity = entity;
        this.session = session;
        Transaction tx = null;
        try {
            // 开始事务
            tx = session.beginTransaction();
            // 持久化
            Serializable result = session.save(entity);
            // 提交事务
            tx.commit();
            this.data = (Integer) result;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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
}
