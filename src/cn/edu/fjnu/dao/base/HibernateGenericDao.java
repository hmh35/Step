package cn.edu.fjnu.dao.base;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 15:06
 * @Description: GenericDAO接口的Hibernate实现
 */
@Repository
@Transactional
public abstract class HibernateGenericDao<T, ID extends Serializable>
        implements GenericDao<T, ID> {
    protected static Logger logger = LoggerFactory
            .getLogger(HibernateGenericDao.class);

    /**
     * 持久化对象的实际类型
     */
    private Class<T> entityClass;

    /**
     * 构造函数(默认)
     */
    public HibernateGenericDao() {
        // 获取当前Class为泛型超类的第一个类型参数
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 如需运行时变更持久化类型，可override本函数。
     *
     * @return 持久化对象类型
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 注入sessionFactory
     */
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 获取session
     *
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 创建Criteria
     *
     * @return
     */
    public Criteria createCriteria() {
        return getSession().createCriteria(entityClass);

    }

    @Override
    public T findById(ID id) {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public T findById(ID id, boolean lock) {
        if (lock) {
            return (T) getSession().get(entityClass, id,
                    LockMode.UPGRADE);
        } else {
            return findById(id);
        }
    }

    @Override
    public List<T> findAll() {
        return createCriteria().list();
    }

    @Override
    public T save(T entity) {
        return (T) getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        T entity = this.findById(id);
        if (entity != null) {
            this.delete(entity);
        }
    }

    @Override
    public boolean exists(ID id) {
        return findById(id) != null;
    }

    @Override
    public List<T> findAllByOrder(String orderBy, boolean isAsc) {
        Criteria criteria = createCriteria();
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }
        return criteria.list();
    }

    @Override
    public List<T> findAllByProperty(Map<String, Object> propertyNameMap) {
        Criteria criteria = createCriteria();
        for (Map.Entry<String, Object> propertyName : propertyNameMap.entrySet()) {
            Criterion criterion;
            if (propertyName.getValue() instanceof Number) {
                criterion = Restrictions.like(propertyName.getKey(), propertyName.getValue());
            } else {
                criterion = Restrictions.like(propertyName.getKey(), "%" + propertyName.getValue() + "%");
            }
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public List<T> findAllByOneProperty(String propertyName, Object value) {
        Criteria criteria = createCriteria();
        Criterion criterion = Restrictions.eq(propertyName,value);
        criteria.add(criterion);
        return criteria.list();
    }

    @Override
    public T uniqueResult(String propertyName, Object value) {
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) createCriteria().add(criterion).uniqueResult();
    }

    /**
     * 缺少2个方法 1.多条件不分页查询 2.多条件分页查询
     */

    @Override
    public List<T> findPage(int pageNo, int pageSize) {

        Criteria criteria = createCriteria();
        // 设置起始结果数
        criteria.setFirstResult((pageNo - 1) * pageSize);
        // 返回的最大结果集
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    public List<T> findAllByProPage(String propertyName, Object value, int pageNo, int pageSize) {

        Criteria criteria = createCriteria();
        Criterion criterion = Restrictions.eq(propertyName,value);
        criteria.add(criterion);
        // 设置起始结果数
        criteria.setFirstResult((pageNo - 1) * pageSize);
        // 返回的最大结果集
        criteria.setMaxResults(pageSize);
        //Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        return criteria.list();
    }
}
