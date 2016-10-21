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

import java.sql.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

import javax.annotation.Resource;


@Repository(value = "activitiesDao")
public class ActivitiesDaoImpl extends HibernateGenericDao<Activities, Integer> implements ActivitiesDao {

    @Override
    public List getActivityByMonitoredOnTime(String monitoredNo, Page page) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String hql = "from Activities WHERE aplyUpplmt>=? AND creatorNo IN (SELECT creatorNo FROM UserAndActivities WHERE monitoredNo=?) AND actNo IN (SELECT actNo FROM UserAndActivities where monitoredNo = ?)";
        Query query = getSession().createQuery(hql).setTimestamp(0,time).setString(1, monitoredNo).setString(2,monitoredNo);
        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        return activitiesList;
    }

    @Override
    public List getActivityByMonitoredOutTime(String monitoredNo, Page page) {
        System.out.println(monitoredNo);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String hql = "from Activities a WHERE a.aplyLowlmt<? AND a.creatorNo IN (SELECT u.creatorNo FROM UserAndActivities u WHERE u.monitoredNo=?) AND a.actNo IN (SELECT u.actNo FROM UserAndActivities u where u.monitoredNo = ?)";
        Query query = getSession().createQuery(hql).setTimestamp(0, time).setString(1, monitoredNo).setString(2,monitoredNo);
        //query.setResultTransformer(Transformers.aliasToBean(Activities.class));
        System.out.println(query);
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List activitiesList = query.list();
        System.out.println();
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

    @Override
    public Activities getActivitiesByCCT(String creatorNo,String createTime){
        System.out.println(createTime);
        //String hql = "from Activities a where a.creatorNo = ? and a.createTime = '2016-10-17 16:40:28'";
        String hql = "from Activities a where a.creatorNo = ? and a.TimeLab =  ?";
        Query query = getSession().createQuery(hql).setString(0,creatorNo).setString(1,createTime);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Activities activities = (Activities) query.uniqueResult();
        System.out.println(activities.getActNo());
        return activities;
    }

}
