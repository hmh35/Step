package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:59
 * @Description:
 */
public interface ContactsService {

    /**
     * 创建新联系人
     *
     * @param contacts
     */
    void createContacts(MonitoredAndMonitor contacts);

    /**
     * 通过用户No（主键）获取所有联系人
     *
     * @param monitoredNo
     * @return
     */
    List<MonitoredAndMonitor> getAllContacts(String monitoredNo, Page page);

    /**
     * 通过用户No（主键）删除对应的联系人
     *
     * @param monitoredUserId
     */
    void deleteContactsByMonitoredAndMonitor(Integer monitoredUserId,MonitoredAndMonitor deleteContacts);

    /**
     * 更新联系人
     */
    void updateContactsByMonitoredAndMonitor(String monitoredNo, String monitorNo,String relationShip);

    void updateContacts(MonitoredAndMonitor contacts);

    List<MonitoredAndMonitor> getProContacts(String userNo);
}