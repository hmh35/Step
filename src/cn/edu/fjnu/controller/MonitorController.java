package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ConfigService;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.utils.Md5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 19:27
 * @Description:
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    private Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Resource
    private MonitorService monitorService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private ConfigService configService;

    @Resource
    private MonitoredService monitoredService;

    /**
     * 监护人登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "userName") String userName, @RequestParam(value = "userPwd") String userPwd) {
            ResultData resultData = new ResultData();
        try {
            System.out.printf("账号："+userName+" "+"密码："+userPwd);
            Monitor monitor = monitorService.login(userName, userPwd);
            //登陆成功后返回加密后的登陆令牌
            resultData.setAccessToken(loginLogService.updateAccesstokenByUserNo(monitor.getUserName()));
            //返回用户客户端系统配置
            resultData.setConfig(JSON.toJSON(configService.getConfig(monitor.getUserName())));
            //返回用户信息
            resultData.setData(JSON.toJSON(monitor));
            //设置返回结果状态
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(resultData, true));
        return JSON.toJSONString(resultData, true);

    }

    /**
     * 监护人注册
     *
     * @param monitor
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String register(String monitor) {
        Monitor saveMonitor = JSON.parseObject(monitor, Monitor.class);
        ResultData resultData = new ResultData();
        try {
            monitorService.saveMonitor(saveMonitor);
            //设置返回密码为空
            saveMonitor.setUserPwd(null);
            //注册成功后返回登陆令牌
            resultData.setAccessToken(loginLogService.createAccesstoken(saveMonitor.getUserName()));
            //返回用户客户端系统配置
            resultData.setConfig(JSON.toJSON(configService.createConfig(saveMonitor.getUserName())));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }


    @ResponseBody
    @RequestMapping(value = "/get/monitored", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getMonitoredByMonitor(@RequestParam(value = "accesstoken") String accesstoken,
                                        @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            Page p = JSONObject.parseObject(page, Page.class);
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            List<Monitored> monitoredList = monitorService.getAllMonitoredByMonitorByPage(monitor.getMonitorNo(), p);
            resultData.setData(JSON.toJSONString(monitoredList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    @ResponseBody
    @RequestMapping(value = "/resetpwd/monitored", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String setMonitoredPwdByMonitor(@RequestParam(value = "accesstoken") String accesstoken,
                                           @RequestParam(value = "studentNo") String studentNo) {
        ResultData resultData = new ResultData();
        try {
            //需要加入权限验证,设置登录过期
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            String newPwd = studentNo.substring(studentNo.length() - 6, studentNo.length());
            monitoredService.updatePassword(studentNo, Md5.digest(newPwd.getBytes()));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    @ResponseBody
    @RequestMapping(value = "/status/monitored", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String setMonitoredStatusByMonitor(@RequestParam(value = "accesstoken") String accesstoken,
                                              @RequestParam(value = "studentNo") String studentNo,
                                              @RequestParam(value = "status") int status) {
        ResultData resultData = new ResultData();
        try {
            //需要加入权限验证,设置登录过期
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            if (status == Monitored.MonitoredStatus.VALID) {
                monitoredService.unforbiddenMonitored(studentNo);
            } else {
                monitoredService.forbiddenMonitored(studentNo);
            }

            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 被监护人信息更新
     * @param monitor
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateMonitorInfo(@RequestParam(value = "monitor") String monitor,
                                      @RequestParam(value = "accesstoken") String accesstoken) {
        Monitor updateMonitor = JSON.parseObject(monitor, Monitor.class);
        ResultData resultData = new ResultData();
        try {
            //通过accesstoken获取
            Monitor mon = monitorService.getMonitorByAccesstoken(accesstoken);
            updateMonitor.setUserName(mon.getUserName());
            monitorService.updateMonitor(updateMonitor);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }
}
