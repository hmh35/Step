package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:56
 * @Description:
 */
public interface ContactsDao extends GenericDao<MonitoredAndMonitor,Integer>{

    /**
     * 通过被监护人No删除
     * @param monitoredNo
     */
    void deleteByMonitoredAndMonitor(String monitoredNo,String monitorNo);

    void updateByMonitoredAndMonitor(String monitoredNo,String monitorNo,String relationShip);

    boolean saveunique(String monitoredUser,String monitorPhone);

    List findProContacts(String userNo);

}