package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.*;
import com.drp.repository.ResourceRepository;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                .addOrder( Order.desc("lastUpdateTime"));
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
                .addOrder( Order.desc("lastUpdateTime"));
        return c.list();
    }

    public RArticleEntity getArticleDetailById(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<RArticleEntity>("RArticleEntity", session, id).getData();
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

    public List<RArticleEntity> getNoticeList(Integer userType, Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(RNoticeEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);

        if(userType != -1) {
            int receiverType;
            // 分销商未登录
            if(userType == 0) {
                receiverType = 1;
            }else { // 已登陆分销商
                receiverType = 2;
                Timestamp s = new Timestamp(new Date().getTime());
                c.add(Restrictions.le("effectDate", s));
                c.add(Restrictions.ge("endDate", s));

            }
            c.add(Restrictions.eq("receiverType", receiverType));
        }
        c.addOrder( Order.desc("effectDate")).addOrder( Order.desc("lastUpdateTime"));
        return c.list();
    }

    public List<PProductPromotionEntity> getPromotionList(Integer userType, Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(PProductPromotionEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);
        if(userType == 0) {

            Timestamp s = new Timestamp(new Date().getTime());
            c.add(Restrictions.le("salesEffStart", s));
            c.add(Restrictions.ge("salesEffEnd", s));
            c.add(Restrictions.eq("status", "3"));
        }
        c.addOrder( Order.desc("salesEffEnd")).addOrder( Order.desc("lastUpdateTime"));
        return c.list();
    }

    public List<RRegionEntity> getProvinceList() {
        Session session = this.getCurrentSession();
        Criteria c = session.createCriteria(RRegionEntity.class);
        c.add(Restrictions.eq("level", 1))
                .addOrder( Order.asc("regionCode"));
        return c.list();
    }

    public List<RRegionEntity> getCityByProvince(String provinceId) {
        Session session = this.getCurrentSession();
        Criteria c = session.createCriteria(RRegionEntity.class);
        c.add(Restrictions.eq("level", 2))
                .add(Restrictions.eq("parentCode", provinceId))
                .addOrder( Order.asc("regionCode"));
        return c.list();
    }

    public List<RRegionEntity> getAreaByCity(String cityId) {
        Session session = this.getCurrentSession();
        Criteria c = session.createCriteria(RRegionEntity.class);
        c.add(Restrictions.eq("level", 3))
                .add(Restrictions.eq("parentCode", cityId))
                .addOrder( Order.asc("regionCode"));
        return c.list();
    }

    public DPointsEntity getPointRule() {
        Session session = this.getCurrentSession();
        Criteria c = session.createCriteria(DPointsEntity.class);
        return (DPointsEntity)c.list().get(0);
    }


}
