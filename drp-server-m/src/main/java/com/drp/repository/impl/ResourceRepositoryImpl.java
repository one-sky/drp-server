package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.RArticleEntity;
import com.drp.entity.RBannerEntity;
import com.drp.repository.ResourceRepository;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public List<RBannerEntity> getBannerList(Integer status, Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(RBannerEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);
        c.add(Restrictions.eq("status", status))
                .addOrder( Order.asc("sortBy"))
                .addOrder( Order.desc("lastUpdateBy"));
        return c.list();
    }

    public RBannerEntity selectBannerByKey(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<RBannerEntity>("RBannerEntity", session, id).getData();
    }

    public Integer insertBanner(RBannerEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<RBannerEntity>(session, entity).getData();
    }

    public Integer updateBanner(RBannerEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<RBannerEntity>(session, entity).getData();
    }

    public List<RArticleEntity> getArticleList(Integer displayArea, Integer status,
                                                Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(RArticleEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);
        c.add(Restrictions.eq("displayArea", displayArea));
        c.add(Restrictions.eq("status", status))
                .addOrder( Order.asc("sortBy"))
                .addOrder( Order.desc("lastUpdateBy"));
        return c.list();
    }

    public RArticleEntity getArticleDetailById(Integer id) {
        return null;
    }

    public RArticleEntity selectArticleByKey(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<RArticleEntity>("RArticleEntity", session, id).getData();
    }

    public Integer insertArticle(RArticleEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<RArticleEntity>(session, entity).getData();
    }

    public Integer updateArticle(RArticleEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<RArticleEntity>(session, entity).getData();
    }


}
