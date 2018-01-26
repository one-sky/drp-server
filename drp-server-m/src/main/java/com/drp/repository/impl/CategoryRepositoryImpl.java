package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.Update;
import com.drp.entity.PCategoryEntity;
import com.drp.repository.CategoryRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public List<PCategoryEntity> getCategoryList() {
        Criteria c = getCurrentSession().createCriteria(PCategoryEntity.class);
        c.add(Restrictions.eq("parentId", 0));
        c.add(Restrictions.eq("status", 1));
        return c.list();

    }

    public PCategoryEntity getCategoryById(Integer id) {
        Criteria c = getCurrentSession().createCriteria(PCategoryEntity.class);
        c.add(Restrictions.eq("id", id));
        c.add(Restrictions.eq("status", 1));
        if(c.list().isEmpty()){
            return null;
        }
        return (PCategoryEntity)c.list().get(0);
    }

    public List<PCategoryEntity> getCategoryByParentId(Integer parentId) {
        Criteria c = getCurrentSession().createCriteria(PCategoryEntity.class);
        c.add(Restrictions.eq("parentId", parentId));
        c.add(Restrictions.eq("status", 1));
        return c.list();
    }

    public List<PCategoryEntity> getCategoryByCategoryName(String categoryName, Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(PCategoryEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);
        c.add(Restrictions.eq("categoryName", categoryName));
        c.add(Restrictions.eq("status", 1))
                .addOrder( Order.asc("sortBy"))
                .addOrder( Order.desc("lastUpdateBy"));
        return c.list();
    }

    public Integer insertCategory(PCategoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<PCategoryEntity>(session, entity).getData();
    }

    public Integer updateCategory(PCategoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<PCategoryEntity>(session, entity).getData();
    }


}
