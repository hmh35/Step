package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import cn.edu.fjnu.utils.Md5;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: linqiu
 * @Date: 2016/3/4 12:28
 * @Description:
 */
@Controller
@RequestMapping("/monitored")
public class MonitoredController {

    private Logger logger = LoggerFactory.getLogger(MonitoredController.class);

    @Resource
    private MonitoredService monitoredService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private ConfigService configService;

    @Resource
    private MonitoredAndMonitorService monitoredAndMonitorService;

    @Resource
    private MonitorService monitorService;

    @Resource
    private ContactsService contactsService;

    /**
     * 被监护人登录
     * @param studentNo
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "studentNo") String studentNo, @RequestParam(value = "password") String password,
                        @RequestParam(value = "channelId") String channelId) {
        ResultData resultData = new ResultData();
        try {
            Monitored monitored = monitoredService.login(studentNo, password);
            //登陆成功后返回加密后的登陆令牌
            resultData.setAccessToken(loginLogService.updateAccesstokenByUserNo(monitored.getStudentNo()));
            //返回用户客户端系统配置
            resultData.setConfig(configService.getConfig(monitored.getStudentNo()));
            //返回用户信息
            resultData.setData(JSON.toJSON(monitored));
            //设置返回结果状态
            resultData.setStatus(ResultData.SUCCESS);

            monitoredService.UpdateChannelId(channelId,monitored.getMonitoredNo());
            //更新channelid
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 被监护人注册
     * @param monitored
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(@RequestParam(value = "monitored") String monitored,
                           @RequestParam(value = "accesstoken") String accesstoken) {

        ResultData resultData = new ResultData();
        try {
            Monitored saveMonitored = JSON.parseObject(monitored, Monitored.class);
            String password = saveMonitored.getStudentNo().substring(saveMonitored.getStudentNo().length() - 6, saveMonitored.getStudentNo().length());
            saveMonitored.setPassword(Md5.digest(password.getBytes()));
            saveMonitored.setStatus(Monitored.MonitoredStatus.VALID);
            Monitor creator = monitorService.getMonitorByAccesstoken(accesstoken);
            monitoredService.saveMonitored(saveMonitored);
            //注册成功后创建accesstoken
            String as = loginLogService.createAccesstoken(saveMonitored.getStudentNo());
            //创建客户端配置
            configService.createConfig(saveMonitored.getStudentNo());
            Monitored afterSave = monitoredService.getMonitoredByAccesstoken(as);
            //创建监护人和被监护人关系
            monitoredAndMonitorService.createMonitoredAndMonitor(afterSave.getMonitoredNo(),creator.getMonitorNo());
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }

    /**
     * 被监护人信息更新
     * @param monitored
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateMonitored(@RequestParam(value = "monitored") String monitored,
                           @RequestParam(value = "accesstoken") String accesstoken) {

        ResultData resultData = new ResultData();
        try {
            Monitored saveMonitored = JSON.parseObject(monitored, Monitored.class);
            Monitor creator = monitorService.getMonitorByAccesstoken(accesstoken);
            monitoredService.updateMonitor(saveMonitored);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }
    /**
     * 被监护人信息更新
     * @param monitored
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateMonitoredInfo(@RequestParam(value = "monitored") String monitored,
                                      @RequestParam(value = "accesstoken") String accesstoken) {
        Monitored updateMonitored = JSON.parseObject(monitored, Monitored.class);
        ResultData resultData = new ResultData();
        try {
            //通过accesstoken获取
            Monitored mon = monitoredService.getMonitoredByAccesstoken(accesstoken);
            updateMonitored.setStudentNo(mon.getStudentNo());
            monitoredService.updateMonitor(updateMonitored);
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
