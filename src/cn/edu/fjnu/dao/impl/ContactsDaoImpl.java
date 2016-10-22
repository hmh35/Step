package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ContactsDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.exception.AppRTException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:57
 * @Description:
 */
@Repository(value = "contactsDao")
public class ContactsDaoImpl extends HibernateGenericDao<MonitoredAndMonitor,Integer> implements ContactsDao {


    @Override
    public void deleteByMonitoredAndMonitor(String monitoredNo,String monitorNo){
        String hql="delete MonitoredAndMonitor m where m.monitoredNo=? And m.monitorNo=?";
        Query query = getSession().createQuery(hql).setString(0,monitoredNo).setString(1, monitorNo);
        query.executeUpdate();
    }
    @Override
    public void updateByMonitoredAndMonitor(String monitoredNo,String monitorNo,String relationShip){
        String hql="update MonitoredAndMonitor m set m.relationShip=? where m.monitoredNo=? And m.monitorNo=?";
        Query query = getSession().createQuery(hql).setString(0,monitoredNo).setString(1, relationShip).setString(2,monitorNo);
        query.executeUpdate();
    }
    @Override
    public boolean saveunique(String monitoredUser,String monitorPhone)
    {
        String hql="from MonitoredAndMonitor m where monitoredNo=? And m.monitorPhone=?";
        Query query = getSession().createQuery(hql).setString(0,monitoredUser).setString(1, monitorPhone);
        MonitoredAndMonitor m=(MonitoredAndMonitor) query.uniqueResult();
        if(m==null)
            return true;
        else
            return false;

    }


    @Override
    public List findProContacts(String userNo) {
        String hql = "from MonitoredAndMonitor m where m.monitoredNo = ? and m.monitorUserId in (select userId from User)";
        Query query = getSession().createQuery(hql).setString(0,userNo);
        return query.list();
    }
}