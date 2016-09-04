package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Monitored;

/**
 * @Author: linqiu
 * @Date: 2016/3/4 10:44
 * @Description:
 */
public interface MonitoredService {

    /**
     * 被监护人登陆
     *
     * @param studentNo 学号
     * @param password  密码
     * @return
     */
    Monitored login(String studentNo, String password);

    /**
     * 新建被监护人
     *
     * @param monitored
     * @return
     */
    void saveMonitored(Monitored monitored);

    /**
     * 更新被监护人信息
     *
     * @param monitored
     */
    void updateMonitor(Monitored monitored);

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(String studentNo, String oldPassword, String newPassword);

    /**
     * 修改密码(此方法只允许监护人操作)
     *
     * @param newPassword
     */
    void updatePassword(String studentNo, String newPassword);
    /**
     * 禁用monitored
     *
     * @param studentNo
     */
    void forbiddenMonitored(String studentNo);

    /**
     * 启用mornitored
     *
     * @param studentNo
     */
    void unforbiddenMonitored(String studentNo);

    /**
     * 通过accesstoken获得monitored
     *
     * @param accesstoken
     * @return
     */
    Monitored getMonitoredByAccesstoken(String accesstoken);
    /*
    * 更新channelid
    * */
    void UpdateChannelId(String channelId, Integer monitoredNo);
}
