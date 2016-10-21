package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.GenericDao;
import org.hibernate.Query;

import java.util.List;


public interface UserDao extends GenericDao<User,Integer>{

    User getMaxAge();

    /**
     * 获取监护人手下的所有被监护人
     * @param monitorNo
     * @return
     */
    List getAllMonitoredByMonitor(Integer monitorNo, String pushObject);

    List getProperMonitoredByMonitor(Integer monitorNo,String pushObject);

    /*
    * 获取监护人的被监护人
    * */
    List getMonitorByMonitoredNo(Integer monitoredNo);

    /*
    * 获取活动对象
    * */
    User getUser(String userId);

    public void UpdateChannelId(String channelId,Integer userId);
}
