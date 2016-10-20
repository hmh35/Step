package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.MonitoredDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.service.MonitoredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/4 10:44
 * @Description:
 */
@Service
public class MonitoredServiceImpl implements MonitoredService {

    private Logger logger = LoggerFactory.getLogger(MonitoredService.class);

    @Resource
    private MonitoredDao monitoredDao;

    @Resource
    private LoginLogService loginLogService;

    @Override
    public Monitored login(String studentNo, String password) {

        if (studentNo == null || studentNo == "" || password == null || password == "") {
            logger.info("login | studentNo or password is not null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户名或密码为空");
        }
        Monitored monitored = monitoredDao.uniqueResult("studentNo", studentNo);
        if (monitored == null) {
            logger.info("login | this monitored is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "用户名不存在");
        }
        if (monitored.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("login | monitored is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已禁用");
        }
        if (!monitored.getPassword().equals(password)) {
            logger.info("login | studentNo or password is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "用户名或密码错误");
        }
        monitored.setPassword(null);
        return monitored;

    }

    @Override
    public void saveMonitored(Monitored monitored) {
        if (monitored == null || monitored.getStudentNo() == null
                || monitored.getStudentNo() == ""
                || monitored.getPassword() == null
                || monitored.getPassword() == "") {
            logger.info("save | this monitored is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "含必填注册项为空");
        }
        Monitored monitoredTemp = monitoredDao
                .uniqueResult("studentNo", monitored.getStudentNo());
        if (monitoredTemp != null) {
            logger.info("save | this monitored is exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "用户已存在");
        }
        monitoredDao.save(monitored);

    }

    @Override
    public void updateMonitored(Monitored monitored) {
        if (monitored == null || monitored.getStudentNo() == null
                || monitored.getStudentNo() == "") {
            logger.info("update | this monitored is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "修改信息错误");
        }
        Monitored monitoredTemp = monitoredDao.uniqueResult("studentNo", monitored.getStudentNo());
        if (monitoredTemp.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("update | monitored is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法修改密码");
        }
        //不能更新的字段
        monitored.setMonitoredNo(monitoredTemp.getMonitoredNo());
        monitored.setPassword(monitoredTemp.getPassword());
        monitored.setCreateTime(monitoredTemp.getCreateTime());
        monitored.setStatus(monitoredTemp.getStatus());
        //可以更新的字段
        monitored.setUpdateTime(new Date());
        monitoredDao.update(monitored);
    }

    @Override
    public void updatePassword(String studentNo, String oldPassword, String newPassword) {
        if (studentNo == null || studentNo == "") {
            logger.info("updatePassowrd | studentNo is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "修改密码失败");
        }
        if (oldPassword == null || oldPassword == "") {
            logger.info("old password is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "旧密码为空");
        }
        if (newPassword == null || newPassword == "") {
            logger.info("updatePassword | new password is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "新密码为空");
        }
        Monitored monitored = monitoredDao.uniqueResult("studentNo", studentNo);
        if (monitored == null) {
            logger.info("updatePassowrd | this monitored is not exitst!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户不存在，修改密码失败");
        }
        if (monitored.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("updatePassword | monitored is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法修改密码");
        }
        if (!monitored.getPassword().equals(oldPassword)) {
            logger.info("updatePassword | studentNo or password is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "旧密码错误");
        }
        monitored.setPassword(newPassword);
        monitored.setUpdateTime(new Date());
        monitoredDao.update(monitored);
    }

    @Override
    public void updatePassword(String studentNo, String newPassword) {
        if (studentNo == null || studentNo == "") {
            logger.info("updatePassowrd | studentNo is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "修改密码失败");
        }
        if (newPassword == null || newPassword == "") {
            logger.info("updatePassword | new password is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "新密码为空");
        }
        Monitored monitored = monitoredDao.uniqueResult("studentNo", studentNo);
        if (monitored == null) {
            logger.info("updatePassowrd | this monitored is not exitst!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户不存在，修改密码失败");
        }
        if (monitored.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("updatePassword | monitored is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法修改密码");
        }
        monitored.setPassword(newPassword);
        monitored.setUpdateTime(new Date());
        monitoredDao.update(monitored);
    }

    @Override
    public void forbiddenMonitored(String studentNo) {
        if (studentNo == null || studentNo == "") {
            logger.info("forbiddenMonitored | studentNo is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户禁用失败");
        }
        Monitored monitored = monitoredDao.uniqueResult("studentNo", studentNo);
        if (monitored == null) {
            logger.info("forbiddenMonitored | this monitored is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "用户不存在,无法禁用");
        }
        if (monitored.getStatus() == Monitored.MonitoredStatus.INVALID) {
            logger.info("forbiddenMonitored | monitored is not available!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户已经被禁用无需再次禁用");
        }
        monitored.setStatus(Monitored.MonitoredStatus.INVALID);
        monitoredDao.update(monitored);
    }

    @Override
    public void unforbiddenMonitored(String studentNo) {
        if (studentNo == null || studentNo == "") {
            logger.info("unforbiddenMonitored | studentNo is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户启用失败");
        }
        Monitored monitored = monitoredDao.uniqueResult("studentNo", studentNo);
        if (monitored == null) {
            logger.info("unforbiddenMonitored | this monitored is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "用户不存在,无法启用");
        }
        if (monitored.getStatus() == Monitored.MonitoredStatus.VALID) {
            logger.info("unforbiddenMonitored | monitored is available!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户已经启用用无需再次启用");
        }
        monitored.setStatus(Monitored.MonitoredStatus.VALID);
        monitoredDao.update(monitored);
    }

    @Override
    public Monitored getMonitoredByAccesstoken(String accesstoken) {
        if(accesstoken == null || accesstoken == ""){
            logger.info("getMonitoredByAccesstoken | accesstoken is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户accesstoken错误");
        }
        //获取用户用户名
        String userName = loginLogService.checkAccesstoken(accesstoken);
        //通过用户名获取用户主键
        Monitored monitored = monitoredDao.uniqueResult("studentNo", userName);
        if (monitored == null) {
            logger.info("getMonitoredByAccesstoken | this monitored is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "用户不存在,无法获取用户信息");
        }
        if (monitored.getStatus().equals(Monitored.MonitoredStatus.INVALID)) {
            logger.info("getMonitoredByAccesstoken | monitored is no available");
            throw new AppRTException(AppExCode.U_NOT_AVAILABLE, "用户已被禁用,无法获取用户信息");
        }
        return monitored;
    }
    public void UpdateChannelId(String channelId, Integer monitoredNo){
        if (monitoredNo == null || channelId == "") {
            logger.info("UpdateChannelId | studentNo or channelId is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "channelId为空");
        }
        monitoredDao.UpdateChannelId(channelId,monitoredNo);
    }

    @Override
    public List<Monitor> getMonitorByMonitoredNo(Integer monitoredNo) {
        if(monitoredNo == null){
            logger.info("monitorNo is null");
            throw new AppRTException(AppExCode.MONITORED_AND_MONITOR_ERROR,"被监护人不存在");
        }
        List<Monitor> monitorlist = monitoredDao.getMonitorByMonitoredNo(monitoredNo);
        return monitorlist;
    }
}
