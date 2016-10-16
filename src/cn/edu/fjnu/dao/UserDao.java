package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Dao接口层
 */
public interface UserDao extends GenericDao<User,Integer>{

    User getMaxAge();

    public void UpdateChannelId(String channelId,Integer userId);

    List getAllMonitoredByMonitor(Integer monitorNo, String pushObject);

    List getProperMonitoredByMonitor(Integer monitorNo,String pushObject);

    List getMonitorByMonitoredNo(Integer monitoredNo);
}
