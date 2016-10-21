package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.dao.MonitorDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.dao.base.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "monitorDao")
public class MonitorDaoImpl extends HibernateGenericDao<Monitor,Integer> implements MonitorDao {

    @Override
    public List getAllMonitoredByMonitorByPage(Integer monitorNo, Page page) {
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo);
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.list();
    }

    @Override
    public List getAllMonitoredByMonitor(Integer monitorNo,String pushObject) {
        System.out.println("5555:"+pushObject);
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo);
        return query.list();
    }

    @Override
    public List getProperMonitoredByMonitor(Integer monitorNo,String pushObject) {
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=? and relationShip=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo).setString(1,pushObject);
        return query.list();
    }

    @Override
    public void UpdateChannelId(String channelId, Integer monitorNo) {
        String hql="update User u set u.channelId = ? where u.userId = ?";
        Query query = getSession().createQuery(hql).setString(0,channelId).setInteger(1, monitorNo);
        query.executeUpdate();
    }

}
