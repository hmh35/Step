package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.LoginLog;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.LoginLogDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.utils.DateUtils;
import cn.edu.fjnu.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 20:15
 * @Description: 登录日志service实现类
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    private Logger logger = LoggerFactory.getLogger(LoginLogServiceImpl.class);

    @Resource
    private LoginLogDao loginLogDao;

    @Override
    public String createAccesstoken(String userName) {
        if(userName == null || userName == ""){
            logger.info("createAccessToken | userName is null!");
            throw  new AppRTException(AppExCode.A_CREATE_ERROR,"参数丢失，创建accesstoken错误");
        }
        LoginLog getLoginLog = loginLogDao.uniqueResult("userName",userName);
        if(getLoginLog != null){
            logger.info("createAccesstoken | this user exits accesstoken!");
            throw  new AppRTException(AppExCode.A_CREATE_ERROR,"该用户存在accesstoken，无需创建新accesstoken!");
        }
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(userName);
        loginLog.setCreateTime(new Date());
        loginLog.setUpdateTime(loginLog.getCreateTime());
        loginLog.setEffictiveTime(DateUtils.laterAMonth());
        String original = userName + loginLog.getCreateTime();
        loginLog.setAccesstoken(Md5.digest(original.getBytes()));

        loginLogDao.saveOrUpdate(loginLog);
        return loginLog.getAccesstoken();
    }

    @Override
    public String updateAccesstoken(String oldAccesstoken) {
        if(oldAccesstoken == null || oldAccesstoken == ""){
            logger.info("updateAccessToken | oldAccesstoken is null!");
            throw  new AppRTException(AppExCode.A_UPDATE_ERROR,"参数丢失，更新accesstoken错误");
        }

        LoginLog loginLog = loginLogDao.uniqueResult("accesstoken",oldAccesstoken);
        if(loginLog == null){
            logger.info("updateAccessToken | oldAccesstoken is error!");
            throw  new AppRTException(AppExCode.A_UPDATE_ERROR,"旧accesstoken错误，更新accesstoken错误");
        }

        //设置更新时间、accesstoken有效时间和新accesstoken
        loginLog.setUpdateTime(new Date());
        loginLog.setEffictiveTime(DateUtils.laterAMonth());
        String original = loginLog.getUserName() + loginLog.getUpdateTime();
        loginLog.setAccesstoken(Md5.digest(original.getBytes()));

        loginLogDao.update(loginLog);
        return loginLog.getAccesstoken();
    }

    @Override
    public String getUserName(String accesstoken) {
        if(accesstoken == null || accesstoken == ""){
            logger.info("getUserNo | accesstoken is null!");
            throw  new AppRTException(AppExCode.A_GET_UNO_ERROR,"accesstoken参数丢失");
        }
        LoginLog loginLog = loginLogDao.uniqueResult("accesstoken",accesstoken);
        if(loginLog == null){
            logger.info("getUserNo | accesstoken is error!");
            throw  new AppRTException(AppExCode.A_GET_UNO_ERROR,"accesstoken错误");
        }
        Date now = new Timestamp(System.currentTimeMillis());
        if(!DateUtils.isLater(loginLog.getEffictiveTime(),now)){
            logger.info("getUserNo | accesstoken is overdue!");
            throw  new AppRTException(AppExCode.ACCESSTOKEN_TIMEOUT,"accesstoken已过期");
        }
        return loginLog.getUserName();
    }

    @Override
    public String checkAccesstoken(String accesstoken) {
        return getUserName(accesstoken);
    }

    @Override
    public String updateAccesstokenByUserNo(String userName) {
        if(userName == null || userName == ""){
            logger.info("updateAccesstokenByUserNo | usrNo is null");
            throw new AppRTException(AppExCode.A_UPDATE_ERROR,"参数丢失");
        }
        LoginLog loginLog = loginLogDao.uniqueResult("userName",userName);
        if(loginLog == null){
            logger.info("updateAccesstokenByUserNo | this user is not exists!");
            throw  new AppRTException(AppExCode.A_CREATE_ERROR,"该用户存不存在!");
        }
        //设置更新信息
        loginLog.setUpdateTime(new Date());
        loginLog.setEffictiveTime(DateUtils.laterAMonth());
        String original = loginLog.getUserName() + loginLog.getUpdateTime();
        loginLog.setAccesstoken(Md5.digest(original.getBytes()));
        loginLogDao.update(loginLog);
        return loginLog.getAccesstoken();
    }

    @Override
    public String getAccesstoken(String userName) {
        if(userName == null || userName == ""){
            logger.info("getAccesstokenByUserNo | usrNo is null");
            throw new AppRTException(AppExCode.A_UPDATE_ERROR,"参数丢失");
        }
        LoginLog loginLog = loginLogDao.uniqueResult("userName",userName);
        System.out.println();
        if(loginLog == null){
            logger.info("getAccesstokenByUserNo | this user is not exists!");
            throw  new AppRTException(AppExCode.A_CREATE_ERROR,"该用户存不存在!");
        }
        return loginLog.getAccesstoken();
    }
}
