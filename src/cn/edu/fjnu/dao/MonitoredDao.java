package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;


public interface MonitoredDao extends GenericDao<Monitored,Integer>{
    public void UpdateChannelId(String channelId,Integer monitoredNo);
    List getMonitorByMonitoredNo(Integer monitoredNo);
}
