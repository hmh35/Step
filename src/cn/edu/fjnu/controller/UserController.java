package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import cn.edu.fjnu.utils.Md5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
        * @Author: linqiu
        * @Date: 2016/3/3 11:00
        * @Description: UserController 测试类控制层
        */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Resource
    private UserService userService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private ConfigService configService;



    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "phoneNum") String phoneNum, @RequestParam(value = "userPwd") String userPwd,
                        @RequestParam(value = "channelId") String channelId) {
        ResultData resultData = new ResultData();
        try {

            User user = userService.login(phoneNum, userPwd);
            //登陆成功后返回加密后的登陆令牌
            resultData.setAccessToken(loginLogService.updateAccesstokenByUserNo(user.getPhoneNum()));
            //返回用户客户端系统配置
           // resultData.setConfig(JSON.toJSON(configService.getConfig(monitor.getUserName())));
            //返回用户信息
            resultData.setData(JSON.toJSON(user));
            //设置返回结果状态
            System.out.println("user："+JSON.toJSON(user));
            userService.UpdateChannelId(channelId,user.getUserId());

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
     * 注册
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(@RequestParam(value = "user") String user)
    {
        ResultData resultData = new ResultData();
        try {
            System.out.println("测试："+user);
            User saveUser = JSON.parseObject(user, User.class);
            System.out.println("111:"+saveUser);
            String password = saveUser.getUserPwd().substring(saveUser.getUserPwd().length() - 6, saveUser.getUserPwd().length());
            saveUser.setUserPwd(Md5.digest(password.getBytes()));
            System.out.println("22:"+saveUser);

            userService.saveUser(saveUser);
            System.out.println("33:"+saveUser);
            //注册成功后创建accesstoken
            String as = loginLogService.createAccesstoken(saveUser.getPhoneNum());
            //创建客户端配置
            configService.createConfig(saveUser.getPhoneNum());
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
