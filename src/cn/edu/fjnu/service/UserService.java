package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service接口层
 */
public interface UserService {
    User login(String userName, String password);

    /**
     * 新增监护人
     *
     * @param monitor
     */
    void saveUser(User user);

    /**
     * 更新监护人信息
     *
     * @param monitor
     */
    void updateUser(User user);

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(String userName, String oldPassword, String newPassword);



    /**
     * 通过accesstoken获取监护人数据
     *
     * @param accesstoken
     * @return
     */
    User getUserByAccesstoken(String accesstoken);

    User getUserByPhoneNum(String phoneNum);


    List<Monitored> getAllMonitoredByMonitorByPage(Integer monitorNo, Page page);

    List<User> getMonitoredByMonitor(Integer monitorNo,String pushObject);


    /*
    * 获取被监护人的所有监护人
    * */
    List<User> getMonitorByMonitoredNo(Integer monitorNo);

    void UpdateChannelId(String channelId, Integer userId);

}
