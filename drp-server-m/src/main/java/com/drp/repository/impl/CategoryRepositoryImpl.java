package com.drp.repository.impl;

import com.drp.Util.Delete;
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
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PCategoryEntity.class);
            if (userType == 1) {
                c.add(Restrictions.eq("status", 1));
            }
            c.addOrder(Order.asc("level")).addOrder(Order.asc("parentId"))
                    .addOrder(Order.asc("sortBy")).addOrder(Order.desc("lastUpdateTime"));

            return c.list();
        }catch (Exception e) {
            return null;
        }finally {
            session.close();
        }

    }

    public PCategoryEntity getCategoryById(Integer id) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PCategoryEntity.class);
            c.add(Restrictions.eq("id", id));
            if (c.list().isEmpty()) {
                return null;
            }
            return (PCategoryEntity) c.list().get(0);
        } catch (Exception e) {
            System.exit(1);
            return null;
        } finally {
            session.close();
        }
    }

    public List<PCategoryAttrEntity> getAttributeList(Integer userType, List<Integer> categoryIds) {
        Session session = this.getCurrentSession();
        try {
            Criteria c =session.createCriteria(PCategoryAttrEntity.class);
            if (null != categoryIds && !categoryIds.isEmpty()) {
                c.add(Restrictions.in("categoryId", categoryIds));
            }
            c.addOrder(Order.asc("categoryId"));
            c.addOrder(Order.desc("lastUpdateTime"));
            return c.list();
        }catch (Exception e) {
            System.exit(1);
            return null;
        } finally {
            session.close();
        }
    }

    public PCategoryAttrEntity getAttrById(Integer id) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PCategoryAttrEntity.class);
            c.add(Restrictions.eq("id", id));
            if (c.list().isEmpty()) {
                return null;
            }
            return (PCategoryAttrEntity) c.list().get(0);
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public PAttrValueEntity getAttrValueById(Integer id) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PAttrValueEntity.class);
            c.add(Restrictions.eq("id", id));
            if (c.list().isEmpty()) {
                return null;
            }
            return (PAttrValueEntity) c.list().get(0);
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<PAttrValueEntity> getAttrValueByIdList(List<Integer> ids) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PAttrValueEntity.class);
            c.add(Restrictions.in("id", ids));
            if (c.list().isEmpty()) {
                return null;
            }
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<PAttrValueEntity> getAttrValueList(Integer userType, List<Integer> attrIds) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PAttrValueEntity.class);
            if (userType == 1) {
                c.add(Restrictions.eq("status", 1));
            }
            if (!attrIds.isEmpty()) {
                c.add(Restrictions.in("attrId", attrIds));
            }
            c.addOrder(Order.asc("attrId"));
            c.addOrder(Order.asc("sortBy"));
            c.addOrder(Order.desc("lastUpdateTime"));
            c.addOrder(Order.asc("id"));
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<PCategoryEntity> getCategoryByParentId(Integer parentId) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PCategoryEntity.class);
            c.add(Restrictions.eq("parentId", parentId));
            c.add(Restrictions.eq("status", 1));
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public List<PCategoryEntity> getCategoryByCategoryName(String categoryName, Integer pageSize, Integer startIndex) {
        Session session = this.getCurrentSession();
        try {
            Criteria c = session.createCriteria(PCategoryEntity.class);
            c.setFirstResult(startIndex);
            c.setMaxResults(pageSize);
            c.add(Restrictions.eq("categoryName", categoryName));
            c.add(Restrictions.eq("status", 1))
                    .addOrder(Order.asc("sortBy"))
                    .addOrder(Order.desc("lastUpdateTime"));
            return c.list();
        }catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public Integer insertCategory(PCategoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<PCategoryEntity>(session, entity).getData();
    }

    public Integer updateCategory(PCategoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<PCategoryEntity>(session, entity).getData();
    }

    public Integer deleteCategory(PCategoryEntity entity) {
        Session session = this.getCurrentSession();
        return new Delete(session, entity).getData();
    }

    public Integer insertAttr(PCategoryAttrEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<PCategoryAttrEntity>(session, entity).getData();
    }

    public Integer updateAttr(PCategoryAttrEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<PCategoryAttrEntity>(session, entity).getData();
    }

    public Integer deleteAttr(PCategoryAttrEntity entity) {
        Session session = this.getCurrentSession();
        return new Delete(session, entity).getData();
    }

    public Integer insertAttrValue(PAttrValueEntity entity) {
        Session session = this.getCurrentSession();
        return new Insert<PAttrValueEntity>(session, entity).getData();
    }

    public Integer updateAttrValue(PAttrValueEntity entity) {
        Session session = this.getCurrentSession();
        return new Update<PAttrValueEntity>(session, entity).getData();
    }

    public Integer updateAttrValueList(List<PAttrValueEntity> entity) {
        Session session = this.getCurrentSession();
        try {
            session.beginTransaction();
            for (PAttrValueEntity attr : entity) {
                session.update(attr);
            }
            session.getTransaction().commit();
            return 1;
        }catch (Exception e) {
            return -1;
        } finally {
            session.close();
        }
    }

    public Integer deleteAttrValue(PAttrValueEntity entity) {
        Session session = this.getCurrentSession();
        return new Delete(session, entity).getData();
    }


}
