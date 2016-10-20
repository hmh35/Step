package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.MonitorDao;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 10:30
 * @Description: monitor service实现类
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    private Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private MonitorDao monitorDao;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private UserDao userDao;

    @Override
    public Monitor login(String userName, String password) {
       if (userName == null || userName == "" || password == null || password == "1234567") {
            logger.info("login | monitor userName or use_pwd is not null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户名或密码为空");
        }
        Monitor monitor = monitorDao.uniqueResult("userName", userName);
        if (monitor == null) {
            logger.info("login | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "监护人用户名不存在");
        }
        if (monitor.getStatus().equals(Monitor.MonitorStatus.INVALID)) {
            logger.info("login | monitor is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "监护人用户已禁用");
        }
        System.out.println("  service"+monitor.getUserPwd()+"  "+password+"  "+monitor.getRealName());
        if (!monitor.getUserPwd().equals(password)) {
            logger.info("login | monitor userName or use_pwd is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "监护人用户名或密码错误");
        }

        monitor.setUserPwd(null);
        return monitor;
    }

    @Override
    public void saveMonitor(Monitor monitor) {
        if (monitor == null || monitor.getUserName() == null
                || monitor.getUserName() == ""
                || monitor.getUserPwd() == null
                || monitor.getUserPwd() == "") {
            logger.info("saveMonitor | this monitor is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人含必填注册项为空");
        }
        Monitor monitorTemp = monitorDao
                .uniqueResult("userName", monitor.getUserName());
        if (monitorTemp != null) {
            logger.info("saveMonitor | this monitor is exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该监护人用户已存在");
        }
        monitorDao.save(monitor);
    }

    @Override
    public void updateMonitor(Monitor monitor) {
        if (monitor == null || monitor.getUserName() == null
                || monitor.getUserName() == "") {
            logger.info("updateMonitor | this monitor is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人修改信息错误");
        }
        Monitor monitorTemp = monitorDao.uniqueResult("userName", monitor.getUserName());
        if (monitorTemp.getStatus().equals(Monitor.MonitorStatus.INVALID)) {
            logger.info("updateMonitor | monitor is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法修改密码");
        }
        //不能更新的字段
        monitor.setUserName(monitorTemp.getUserName());
        monitor.setUserPwd(monitorTemp.getUserPwd());
        monitor.setCreateTime(monitorTemp.getCreateTime());
        monitor.setStatus(monitorTemp.getStatus());
        //可以更新的字段
        monitor.setUpdateTime(new Date());
        monitorDao.update(monitor);
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
            logger.info("updatePassword | userName or password is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "监护人旧密码错误");
        }
        monitor.setUserPwd(newPassword);
        monitor.setUpdateTime(new Date());
        monitorDao.update(monitor);
    }

    @Override
    public void forbiddenMonitored(String userName) {
        if (userName == null || userName == "") {
            logger.info("forbiddenMonitor | userName is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户禁用失败");
        }
        Monitor monitor = monitorDao.uniqueResult("userName", userName);
        if (monitor == null) {
            logger.info("forbiddenMonitor | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该监护人用户不存在,无法禁用");
        }
        if (monitor.getStatus() == Monitor.MonitorStatus.INVALID) {
            logger.info("forbiddenMonitor | monitor is not available!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "该监护人用户已经被禁用无需再次禁用");
        }
        monitor.setStatus(Monitor.MonitorStatus.INVALID);
        monitorDao.update(monitor);
    }

    @Override
    public void unforbiddenMonitored(String userName) {
        if (userName == null || userName == "") {
            logger.info("unforbiddenMonitor | userName is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户启用失败");
        }
        Monitor monitor = monitorDao.uniqueResult("userName", userName);
        if (monitor == null) {
            logger.info("unforbiddenMonitor | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该监护人用户不存在,无法启用");
        }
        if (monitor.getStatus() == Monitor.MonitorStatus.VALID) {
            logger.info("unforbiddenMonitor | monitor is not available!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "该监护人用户已经被启用无需再次禁用");
        }
        monitor.setStatus(Monitor.MonitorStatus.VALID);
        monitorDao.update(monitor);
    }

    @Override
    public Monitor getMonitorByAccesstoken(String accesstoken) {
        if(accesstoken == null || accesstoken == ""){
            logger.info("getMonitroByAccesstoken | accesstoken is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "被监护人用户accesstoken错误");
        }
        //获取用户用户名
        String userName = loginLogService.checkAccesstoken(accesstoken);
        //通过用户名获取用户主键
        Monitor monitor = monitorDao.uniqueResult("userName", userName);
        if (monitor == null) {
            logger.info("getMonitroByAccesstoken | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "被监护人用户不存在,无法获取用户信息");
        }
        if (monitor.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("getMonitroByAccesstoken | monitor is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "被监护人用户已被禁用,无法获取用户信息");
        }
        return monitor;
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

    @Override
    public List<User> getMonitoredByMonitor(Integer monitorNo, String pushObject) {
        if(monitorNo == null||pushObject==""){
            logger.info("getAllMonitoredByMonitor | monitorNo is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "无法获取所有被监护人");
        }
        List<User> monitoredList;
        if(pushObject.equals("所有人"))  //字符串判等要用equals，不能用==
        {
            monitoredList = userDao.getAllMonitoredByMonitor(monitorNo,pushObject);
        }
        else
            monitoredList=monitorDao.getProperMonitoredByMonitor(monitorNo,pushObject);
        if(monitoredList.size() == 0){
            logger.info("getAllMonitoredByMonitor | monitoredList is null!");
            throw new AppRTException(AppExCode.U_NOT_FIND_MONITORED, "不存在对应的被监护人");
        }
        return monitoredList;
    }

    @Override
    public void UpdateChannelId(String channelId, Integer monitorNo) {
        if (monitorNo == null || channelId == "") {
            logger.info("UpdateChannelId | studentNo or channelId is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "channelId为空");
        }
        monitorDao.UpdateChannelId(channelId,monitorNo);
    }
}
