package com.drp.repository.impl;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.RBrandEntity;
import com.drp.repository.ManageRepository;
import com.drp.vo.ManageUserVO;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ManageRepositoryImpl implements ManageRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public List<ManageUserVO> getUserList(ManageUserVO entity, Integer pageSize, Integer startIndex) {
        Session session = this.getCurrentSession();
        List<ManageUserVO> data;
        String sqlString="select a.id as userId, b.id as distributorId, a.nickname as nickName, b.name, \n" +
                "b.contractor, b.phone, b.email, b.sexual, b.qq, b.ww_id as wwId, b.province, b.provinceDesc, \n" +
                " b.city, b.cityDesc, b.area, b.areaDesc, b.contract_address as contractAddress, b.comment,\n" +
                " b.head_url as headUrl, b.vip_id as vipId, b.points, b.total_amount as totalAmount, \n" +
                " a.create_time as createTime, a.last_login_time as lastUpdateTime from u_external_user as a " +
                "left join d_distributor as b on a.id = b.user_id where a.type = \"D\" ";

        String condition = "";
        if (!StringUtils.isEmpty(entity.getNickName())) {
            condition = condition + "and a.nickname like '%" + entity.getNickName() + "%' ";
        }
        if (0!=entity.getUserId()) {
            condition = condition + " and a.id =" + entity.getUserId();
        }
        if (null!=entity.getVipId() && entity.getVipId()!=0) {
            condition = condition + " and b.vip_id =" + entity.getVipId();
        }
        sqlString  = sqlString + condition;
        SQLQuery sqlQuery=session.createSQLQuery(sqlString);
        data = sqlQuery.setResultTransformer(Transformers.aliasToBean(ManageUserVO.class)).list();
        return data;
    }

    public List<ManageUserVO> getDistributorVip(List<ManageUserVO> entity) {
        Session session = this.getCurrentSession();
        List<ManageUserVO> data = new ArrayList<ManageUserVO>();
        session.beginTransaction();
        for(ManageUserVO vip:entity) {
            // 获取原价
            String sqlString="select vip_level as vipName, " +
                    "(select vip_level from d_vip where level_code = (? + 1)) as nextVipName, " +
                    "cast(((select points from d_vip where level_code = ? + 1) - (select points from d_distributor where id = ?)) as nchar(10)) " +
                    "as nextLevelPoints from d_vip where level_code = ?";
            SQLQuery sqlQuery=session.createSQLQuery(sqlString);
            sqlQuery.setParameter(0, vip.getVipId());
            sqlQuery.setParameter(1, vip.getVipId());
            sqlQuery.setParameter(2, vip.getDistributorId());
            sqlQuery.setParameter(3, vip.getVipId());
            ManageUserVO vo = (ManageUserVO) sqlQuery.setResultTransformer(Transformers.aliasToBean(ManageUserVO.class)).list().get(0);
            vo.setDistributorId(vip.getDistributorId());
            data.add(vo);
        }
        session.getTransaction().commit();

        return data;
    }

    public List<RBrandEntity> getBrandList(RBrandEntity entity, Integer pageSize, Integer startIndex) {
        Criteria c = getCurrentSession().createCriteria(RBrandEntity.class);
        c.setFirstResult(startIndex);
        c.setMaxResults(pageSize);
        if(entity.getCategoryId() > 0) {
            c.add(Restrictions.eq("categoryId", entity.getCategoryId()));
        }
        if(null!=entity.getIsFinished()) {
            c.add(Restrictions.eq("isFinished", entity.getIsFinished()));
        }
        if(null!=entity.getBrandName()) {
            c.add(Restrictions.like("brandName", "%" + entity.getBrandName() + "%"));
        }

        c.addOrder( Order.desc("createTime"));
        return c.list();
    }
}
