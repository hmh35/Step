package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.dao.ActivitiesDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.dao.base.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 * @Author: linqiu
 * @Date: 2016/3/8 20:29
 * @Description:
 */
@Repository(value = "activitiesDao")
public class ActivitiesDaoImpl extends HibernateGenericDao<Activities, Integer> implements ActivitiesDao {

    @Override
    public List getActivityByMonitoredOnTime(String monitoredNo, Page page) {
        //String sql = "SELECT * from t_activities WHERE CREATOR_NO IN (SELECT MONITOR_NO FROM t_monitored_and_monitor WHERE MONITORED_NO=?);";
        String hql = "from Activities WHERE pushObject='所有人' OR pushObject IN(SELECT relationShip FROM MonitoredAndMonitor WHERE monitoredNo=?) AND creatorNo IN (SELECT monitorNo FROM MonitoredAndMonitor WHERE monitoredNo=?) AND aplyUpplmt>=?";
        Query query = getSession().createQuery(hql).setString(0, monitoredNo).setString(1, monitoredNo).setTimestamp(2, new Date());
        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        return activitiesList;
    }

    @Override
    public List getActivityByMonitoredOutTime(String monitoredNo, Page page) {
        String hql = "from Activities WHERE pushObject='所有人' OR pushObject IN(SELECT relationShip FROM MonitoredAndMonitor WHERE monitoredNo=?) AND creatorNo IN (SELECT monitorNo FROM MonitoredAndMonitor WHERE monitoredNo=?) AND aplyUpplmt<?";
        Query query = getSession().createQuery(hql).setString(0, monitoredNo).setString(1, monitoredNo).setTimestamp(2, new Date());
        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        return activitiesList;
    }

    @Override
    public List getActivityByMonitor(String monitorNo, boolean isOnIime, Page page) {
        Criteria criteria = createCriteria();
        Criterion criterion = Restrictions.eq("creatorNo", monitorNo);
        Criterion aplyUpplmt;
        if (isOnIime) {
            aplyUpplmt = Restrictions.gt("aplyUpplmt", new Date());//new Date()得到当前时间
        } else {
           aplyUpplmt = Restrictions.le("aplyUpplmt", new Date());
        }
        criteria.add(criterion).add(aplyUpplmt).addOrder(Order.desc("aplyUpplmt"));
        criteria.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        criteria.setMaxResults(page.getPageSize());
        return criteria.list();
    }


}
