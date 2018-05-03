package com.drp.repository.impl;

import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.DChannelEntity;
import com.drp.entity.RChannelResourceEntity;
import com.drp.repository.ChannelRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public List<RChannelResourceEntity> getChannelOptionList(Integer userType) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(RChannelResourceEntity.class);
            if (userType == 1) {
                c.add(Restrictions.eq("status", 1));
            }
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public RChannelResourceEntity selectChannelOptionByKey(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<RChannelResourceEntity>("RChannelResourceEntity", session, id).getData();
    }

    public Integer insertChannelOption(RChannelResourceEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<RChannelResourceEntity>(session, entity).getData();
    }

    public Integer updateChannelOption(RChannelResourceEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<RChannelResourceEntity>(session, entity).getData();
    }

    public List<DChannelEntity> getChannelList(Integer distributorId) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(DChannelEntity.class);
            c.add(Restrictions.eq("distributorId", distributorId));
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public DChannelEntity getChannelById(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<DChannelEntity>("DChannelEntity", session, id).getData();
    }

    public Integer insertChannel(DChannelEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<DChannelEntity>(session, entity).getData();
    }

    public Integer updateChannel(DChannelEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<DChannelEntity>(session, entity).getData();
    }
}
