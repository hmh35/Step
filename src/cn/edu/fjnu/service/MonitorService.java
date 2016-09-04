package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 10:30
 * @Description: monitor service层
 */
public interface MonitorService {

    /**
     * 监护人登陆
     *
     * @param userName
     * @param password
     * @return
     */
    Monitor login(String userName, String password);

    /**
     * 新增监护人
     *
     * @param monitor
     */
    void saveMonitor(Monitor monitor);

    /**
     * 更新监护人信息
     *
     * @param monitor
     */
    void updateMonitor(Monitor monitor);

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(String userName, String oldPassword, String newPassword);

    /**
     * 禁用monitor
     *
     * @param userName
     */
    void forbiddenMonitored(String userName);

    /**
     * 启用mornitored
     *
     * @param userName
     */
    void unforbiddenMonitored(String userName);

    /**
     * 通过accesstoken获取监护人数据
     *
     * @param accesstoken
     * @return
     */
    Monitor getMonitorByAccesstoken(String accesstoken);

    /**
     * 分页获取监护人手下的所有被监护人
     * @param monitorNo
     * @return
     */
    List<Monitored> getAllMonitoredByMonitorByPage(Integer monitorNo, Page page);

    /**
     * 获取监护人手下的所有被监护人
     * @param monitorNo
     * @return
     */
    List<Monitored> getMonitoredByMonitor(Integer monitorNo,String pushObject);
}
