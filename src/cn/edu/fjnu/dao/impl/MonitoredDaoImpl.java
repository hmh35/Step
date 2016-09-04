package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.MonitoredDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: linqiu
 * @Date: 2016/3/4 10:43
 * @Description:
 */
@Repository(value = "monitoredDao")
   public class MonitoredDaoImpl extends HibernateGenericDao<Monitored,Integer> implements MonitoredDao{


    @Override
    public void UpdateChannelId(String channelId, Integer monitoredNo) {
        String hql="update Monitored m set m.channelId = ? where monitoredNo = ?";
        Query query = getSession().createQuery(hql).setString(0,channelId).setInteger(1, monitoredNo);
        query.executeUpdate();
    }
}
