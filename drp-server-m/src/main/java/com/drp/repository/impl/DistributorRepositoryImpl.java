package com.drp.repository.impl;

import com.drp.Util.Delete;
import com.drp.Util.Insert;
import com.drp.Util.SelectByPrimarkKey;
import com.drp.Util.Update;
import com.drp.entity.DAddressEntity;
import com.drp.entity.DAgentBrandEntity;
import com.drp.entity.DDistributorEntity;
import com.drp.entity.RBrandEntity;
import com.drp.repository.DistributorRepository;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return new SelectByPrimarkKey<DDistributorEntity>("DDistributorEntity", session, id).getData();
    }

    public DDistributorEntity getDistributorByUserId(Integer id) {
        Criteria c = getCurrentSession().createCriteria(DDistributorEntity.class);
        c.add(Restrictions.eq("userId", id));
        DDistributorEntity result = (DDistributorEntity)c.list().get(0);
        return result;
    }

    public DDistributorEntity getDistributorVip(Integer distributorId, Integer vipId) {
        Session session = this.getCurrentSession();
        DDistributorEntity data;
        // 获取原价
        String sqlString="select vip_level as vipName, " +
                "(select vip_level from d_vip where level_code = " + vipId + ")  as nextVipName, " +
                "cast(((select points from d_vip where level_code = " + vipId + ") - (select points from d_distributor where id = " + distributorId + ")) as nchar(10)) " +
                "as nextLevelPoints from d_vip where level_code = " + vipId;
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        data = (DDistributorEntity) sqlQuery.setResultTransformer(Transformers.aliasToBean(DDistributorEntity.class)).list().get(0);
        return data;
    }

    public List<DAddressEntity> getAddressList(Integer id) {
        Criteria c = getCurrentSession().createCriteria(DAddressEntity.class);
        c.add(Restrictions.eq("distributorId", id))
                .addOrder( Order.desc("isDefault"))
                .addOrder( Order.desc("lastUpdateBy"));
        return c.list();
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

}
