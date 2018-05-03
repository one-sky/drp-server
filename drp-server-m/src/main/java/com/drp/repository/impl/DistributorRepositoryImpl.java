package com.drp.repository.impl;

import com.drp.Util.Delete;
import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.*;
import com.drp.repository.DistributorRepository;
import com.drp.vo.SearchVO;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

@Repository
public class DistributorRepositoryImpl implements DistributorRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }


    public DDistributorEntity getDistributorDetail(Integer id) {
        Session session = this.getCurrentSession();
        DDistributorEntity entity = new SelectByPrimarkKey<DDistributorEntity>("DDistributorEntity", session, id).getData();
        return entity;
    }

    public DDistributorEntity getDistributorByUserId(Integer id) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(DDistributorEntity.class);
            c.add(Restrictions.eq("userId", id));
            DDistributorEntity result = (DDistributorEntity) c.list().get(0);
            return result;
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public Integer updateDistributor(DDistributorEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<DDistributorEntity>(session, entity).getData();
    }

    public DDistributorEntity getDistributorVip(Integer distributorId, Integer vipId) {
        Session session = this.getCurrentSession();
        try {
            DDistributorEntity data;
            // 获取原价
            String sqlString = "select vip_level as vipName, " +
                    "(select vip_level from d_vip where level_code = " + (vipId + 1) + ")  as nextVipName, " +
                    "cast(((select points from d_vip where level_code = " + (vipId + 1) + ") - (select points from d_distributor where id = " + distributorId + ")) as nchar(10)) " +
                    "as nextLevelPoints from d_vip where level_code = " + vipId;
            SQLQuery sqlQuery = session.createSQLQuery(sqlString);
            data = (DDistributorEntity) sqlQuery.setResultTransformer(Transformers.aliasToBean(DDistributorEntity.class)).list().get(0);
            return data;
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<DAddressEntity> getAddressList(Integer id) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(DAddressEntity.class);
            c.add(Restrictions.eq("distributorId", id))
                    .addOrder(Order.desc("isDefault"))
                    .addOrder(Order.desc("lastUpdateTime"));
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public DAddressEntity getAddress(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<DAddressEntity>("DAddressEntity", session, id).getData();
    }

    public DAddressEntity selectAddressByKey(Integer id) {
        Session session = this.getCurrentSession();
        return new SelectByPrimarkKey<DAddressEntity>("DAddressEntity", session, id).getData();
    }

    public Integer insertAddress(DAddressEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<DAddressEntity>(session, entity).getData();
    }

    public Integer updateAddress(DAddressEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<DAddressEntity>(session, entity).getData();
    }

    public Integer deleteAddress(Integer id) {
        Session session = this.getCurrentSession();
        DAddressEntity entity = new DAddressEntity();
        entity.setId(id);
        return new Delete(session, entity).getData();
    }

    public Integer insertPoint(DPointsHistoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<DPointsHistoryEntity>(session, entity).getData();
    }

    public List<DPointsHistoryEntity> getPointList(SearchVO entity) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(DPointsHistoryEntity.class);
            c.add(Restrictions.eq("distributorId", entity.getDistributorId()));

            if (null != entity.getStartDate() && !"".equals(entity.getStartDate())) {
                c.add(Restrictions.ge("orderTime", entity.getStartDate()));

            }
            if (null != entity.getEndDate() && !"".equals(entity.getEndDate())) {
                c.add(Restrictions.le("orderTime", entity.getEndDate()));

            }
            if (null != entity.getOrderCode() && !"".equals(entity.getOrderCode())) {
                c.add(Restrictions.ge("orderNumber", entity.getOrderCode()));

            }
            c.addOrder(Order.desc("lastUpdateTime"));
            c.setFirstResult(entity.getStartIndex());
            c.setMaxResults(entity.getPageSize());
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

}
