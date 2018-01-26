package com.drp.repository.impl;

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
        Criteria c = getCurrentSession().createCriteria(UExternalUserEntity.class);
        c.add(Restrictions.eq("phone", phone));
        c.add(Restrictions.eq("password", password));
        if(c.list().isEmpty()){
            return null;
        }else{
            UExternalUserEntity result = (UExternalUserEntity)c.list().get(0);
            return result;
        }


    }

    public List<UExternalUserEntity> getUserList(List<Integer> userIds) {
        return null;
    }

    public DDistributorEntity register(String phone, String password) {
        return null;
    }

    public boolean checkMobileRegister(String phone) {
        return false;
    }
}
