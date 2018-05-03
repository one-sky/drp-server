package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.Update;
import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;
import com.drp.repository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public UExternalUserEntity login(String phone, String password){
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(UExternalUserEntity.class);
            c.add(Restrictions.eq("phone", phone));
            c.add(Restrictions.eq("password", password));
            if (c.list().isEmpty()) {
                return null;
            } else {
                UExternalUserEntity result = (UExternalUserEntity) c.list().get(0);
                return result;
            }
        }catch (Exception e) {
            System.exit(1);
            return null;
        } finally {
            session.close();
        }


    }

    public List<UExternalUserEntity> getUserList(List<Integer> userIds) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(UExternalUserEntity.class);
            c.add(Restrictions.in("id", userIds));
            if(c.list().isEmpty()){
                return null;
            }else{
                return c.list();
            }
        }catch (Exception e) {
            System.exit(1);
            return null;
        } finally {
            session.close();
        }

    }

    public String register(UExternalUserEntity entity) {
        Session session = this.getCurrentSession();
        Integer data = new Insert<UExternalUserEntity>(session, entity).getData();
        return data + "";

    }

    public boolean checkPhoneRegister(String phone) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(UExternalUserEntity.class);
            c.add(Restrictions.eq("phone", phone));
            return !c.list().isEmpty();
        }catch (Exception e) {
            System.exit(1);
            return false;
        } finally {
            session.close();
        }
    }

    public Integer updateUser(UExternalUserEntity entity) {
        Session session = this.getCurrentSession();
        Integer data = new Update<UExternalUserEntity>(session, entity).getData();
        return data;
    }

    public Integer insertDistributor(DDistributorEntity entity) {
        Session session = this.getCurrentSession();
        Integer data = new Insert<DDistributorEntity>(session, entity).getData();
        return data;
    }
}
