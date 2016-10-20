package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.UserAndActivities;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.MonitorDao;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service实现层
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private MonitorDao monitorDao;

    @Resource
    private UserDao userDao;

    @Resource
    private LoginLogService loginLogService;

    @Override
    public User login(String phoneNum, String userPwd) {
        if (phoneNum == null || phoneNum == "" || userPwd == null ) {
            logger.info("login | monitor userName or use_pwd is not null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户或密码为空");
        }
        User user = userDao.uniqueResult("phoneNum", phoneNum);
        if (user == null) {
            logger.info("login | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "监护人用户名不存在");
        }

        System.out.println("  service"+user.getUserPwd()+"  "+userPwd+"  "+user.getRealName());
        if (!user.getUserPwd().equals(userPwd)) {
            logger.info("login | monitor userName or use_pwd is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "监护人用户名或密码错误");
        }

        user.setUserPwd(null);
        return user;
    }
    @Override
    public void saveUser(User user) {
        if (user == null || user.getPhoneNum() == null
                || user.getPhoneNum() == ""
                || user.getUserPwd() == null
                || user.getUserPwd() == "") {
            logger.info("saveMonitor | this monitor is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人含必填注册项为空");
        }
        User userTemp = userDao
                .uniqueResult("userName", user.getPhoneNum());
        if (userTemp != null) {
            logger.info("saveMonitor | this monitor is exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该监护人用户已存在");
        }
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        if (user == null || user.getPhoneNum() == null
                || user.getPhoneNum() == "") {
            logger.info("updateMonitor | this monitor is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人修改信息错误");
        }
        Monitor monitorTemp = monitorDao.uniqueResult("userName", user.getPhoneNum());
        if (monitorTemp.getStatus().equals(Monitor.MonitorStatus.INVALID)) {
            logger.info("updateMonitor | monitor is no availabl");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法修改密码");
        }
        //不能更新的字段

        user.setUserPwd(monitorTemp.getUserPwd());
        user.setCreateTime(monitorTemp.getCreateTime());

        //可以更新的字段
        user.setUpdateTime(new Date());
        userDao.update(user);
    }

    @Override
    public void updatePassword(String userName, String oldPassword, String newPassword) {
        if (userName == null || userName == "") {
            logger.info("updatePassowrd | userName is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人修改密码失败");
        }
        if (oldPassword == null || oldPassword == "") {
            logger.info("old password is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人旧密码为空");
        }
        if (newPassword == null || newPassword == "") {
            logger.info("new password is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人新密码为空");
        }
        Monitor monitor = monitorDao.uniqueResult("userName", userName);
        if (monitor == null) {
            logger.info("updatePassowrd | this monitor is not exitst!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户不存在，修改密码失败");
        }
        if (monitor.getStatus().equals(Monitor.MonitorStatus.INVALID)) {
            logger.info("updatePassword | monitor is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "监护人用户已被禁用,无法修改密码");
        }
        if (!monitor.getUserPwd().equals(oldPassword)) {
            logger.info("updatePassword | userName or password is no valid");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "监护人旧密码错误");
        }
        monitor.setUserPwd(newPassword);
        monitor.setUpdateTime(new Date());
        monitorDao.update(monitor);
    }
    @Override
    public User getUserByAccesstoken(String accesstoken) {
        if(accesstoken == null || accesstoken == ""){
            logger.info("getUserByAccesstoken | accesstoken is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户accesstoken错误");
        }
        //获取用户用户名
        String userName = loginLogService.checkAccesstoken(accesstoken);
        System.out.println(userName);
        //通过电话号码获取用户
        User user = userDao.uniqueResult("phoneNum", userName);
        if (user == null) {
            logger.info("getUserByAccesstoken | this user is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该用户不存在,无法获取用户信息");
        }

        return user;
    }

    @Override
    public List<Monitored> getAllMonitoredByMonitorByPage(Integer monitorNo, Page page) {
        if(monitorNo == null){
            logger.info("getAllMonitoredByMonitor | monitorNo is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "无法获取所有被监护人");
        }
        List<Monitored> monitoredList = monitorDao.getAllMonitoredByMonitorByPage(monitorNo,page);
        if(monitoredList.size() == 0){
            logger.info("getAllMonitoredByMonitor | monitoredList is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "不存在对应的被监护人");
        }
        return monitoredList;
    }

/*    @Override
    public List<User> getMonitoredByMonitor(Integer monitorNo,UserAndActivities userAndActivities) {
        if(monitorNo == null){
            logger.info("getAllMonitoredByMonitor | monitorNo is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "无法获取相应的被监护人");
        }
        List<User> monitoredList;
        System.out.println(userAndActivities);
        if(userAndActivities.getMonitored_No())  //字符串判等要用equals，不能用==
        {
            monitoredList = userDao.getAllMonitoredByMonitor(monitorNo,pushObject);
        }
        else
            monitoredList=userDao.getProperMonitoredByMonitor(monitorNo,pushObject);
        if(monitoredList.size() == 0){
            logger.info("getAllMonitoredByMonitor | monitoredList is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "不存在对应的被监护人");
        }
        return monitoredList;
    }*/

    @Override
    public List<User> getMonitorByMonitoredNo(Integer monitoredNo) {
        if(monitoredNo == null){
            logger.info("userNo is null");
            throw new AppRTException(AppExCode.MONITORED_AND_MONITOR_ERROR,"被监护人不存在");
        }
        List<User> monitorlist = userDao.getMonitorByMonitoredNo(monitoredNo);
        return monitorlist;
    }

    @Override
    public User getPushObjectByuserId(String userId) {
        if(userId == null || userId == "") {
            logger.info("userId is null");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "该对象不存在");
        }
        User user = userDao.getUser(userId);
        if(user == null){
            logger.info("user is null");
            throw new AppRTException(AppExCode.U_COMMON_ERROR,"用户不存在");
        }
        return user;
    }

    @Override
    public User getUserByPhoneNum(String phoneNum)
    {
        if(phoneNum==null||phoneNum==null)
        {
            logger.info("phoneNum is null!!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "phoneNum为空");
        }
        User user=userDao.uniqueResult("phoneNum",phoneNum);
        return user;
    }

    @Override
    public void UpdateChannelId(String channelId, Integer userId){
        if (userId == null || channelId == "") {
            logger.info("UpdateChannelId | studentNo or channelId is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "channelId为空");
        }
        userDao.UpdateChannelId(channelId,userId);
    }

}
