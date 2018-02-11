package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.Update;
import com.drp.entity.PAttrValueEntity;
import com.drp.entity.PCategoryAttrEntity;
import com.drp.entity.PCategoryEntity;
import com.drp.repository.CategoryRepository;
import com.drp.vo.AttrVO;
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

    public List<PCategoryEntity> getCategoryList(Integer userType) {
        Criteria c = getCurrentSession().createCriteria(PCategoryEntity.class);
        if(userType == 1) {
            c.add(Restrictions.eq("status", 1));
        }
        c.addOrder( Order.asc("level")).addOrder( Order.asc("parentId"))
                .addOrder( Order.asc("sortBy")).addOrder( Order.desc("lastUpdateTime"));

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

    public List<PCategoryAttrEntity> getAttributeList(Integer userType, List<Integer> categoryIds) {
        Criteria c = getCurrentSession().createCriteria(PCategoryAttrEntity.class);
        if(userType == 1) {
            c.add(Restrictions.eq("status", "Y"));
        }
        if(!categoryIds.isEmpty()) {
            c.add(Restrictions.in("categoryId", categoryIds));
        }
        c.addOrder( Order.asc("categoryId"));
        c.addOrder( Order.desc("lastUpdateTime"));
        return c.list();
    }

    public List<PAttrValueEntity> getAttrValueList(Integer userType, List<Integer> attrIds) {
        Criteria c = getCurrentSession().createCriteria(PAttrValueEntity.class);
        if(userType == 1) {
            c.add(Restrictions.eq("status", "Y"));
        }
        if(!attrIds.isEmpty()) {
            c.add(Restrictions.in("attrId", attrIds));
        }
        c.addOrder( Order.asc("attrId"));
        c.addOrder( Order.asc("sortBy"));
        c.addOrder( Order.desc("lastUpdateTime"));
        c.addOrder( Order.asc("id"));
        return c.list();
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
                .addOrder( Order.desc("lastUpdateTime"));
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
