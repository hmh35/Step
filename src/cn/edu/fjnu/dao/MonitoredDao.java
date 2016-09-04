package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 14:28
 * @Description:
 */
public interface MonitoredDao extends GenericDao<Monitored,Integer>{
    public void UpdateChannelId(String channelId,Integer monitoredNo);
}
