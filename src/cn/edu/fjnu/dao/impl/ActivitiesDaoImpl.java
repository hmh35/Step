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
        String hql = "from Activities m WHERE m.aplyUpplmt>=? AND (m.pushObject='所有人' OR m.pushObject IN(SELECT n.relationShip FROM MonitoredAndMonitor n WHERE n.monitoredNo=?) AND m.creatorNo IN (SELECT n.monitorNo FROM MonitoredAndMonitor n WHERE n.monitoredNo=?)) ";
        Query query = getSession().createQuery(hql).setTimestamp(0, new Date()).setString(1, monitoredNo).setString(2, monitoredNo);

        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        System.out.println("执行 进行中的活动"+new Date());
        return activitiesList;
    }

    @Override
    public List getActivityByMonitoredOutTime(String monitoredNo, Page page) {
        String hql = "from Activities m WHERE m.aplyUpplmt<? AND (m.pushObject='所有人' OR m.pushObject IN(SELECT relationShip FROM MonitoredAndMonitor WHERE monitoredNo=?) AND m.creatorNo IN (SELECT monitorNo FROM MonitoredAndMonitor WHERE monitoredNo=?)) ";
        Query query = getSession().createQuery(hql).setTimestamp(0, new Date()).setString(1, monitoredNo).setString(2, monitoredNo);
        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        System.out.println(query);
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        System.out.println();
        System.out.println("执行 过期活动");
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
