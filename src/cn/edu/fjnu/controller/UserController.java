package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
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
    public String login(@RequestParam(value = "phoneNum") String phoneNum, @RequestParam(value = "userPwd") String userPwd) {
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


}
