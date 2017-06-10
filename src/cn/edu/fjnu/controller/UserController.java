package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import cn.edu.fjnu.utils.Md5;
import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet{

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private SynamicService synamicService;

    @Resource
    private LinkDataService linkDataService;




    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "phoneNum") String phoneNum,
                        @RequestParam(value = "userPwd") String userPwd,
                        @RequestParam(value = "channelId") String channelId) {
        ResultData resultData = new ResultData();
        try {
            System.out.println("密码："+userPwd);
          //  saveUser.setUserPwd(Md5.digest(saveUser.getUserPwd().getBytes()));
            String userPwd1=Md5.digest(userPwd.getBytes());
            User user = userService.login(phoneNum, userPwd1);
            //登陆成功后返回加密后的登陆令牌
            resultData.setAccessToken(loginLogService.updateAccesstokenByUserNo(user.getPhoneNum()));
            //返回用户客户端系统配置
            // resultData.setConfig(JSON.toJSON(configService.getConfig(monitor.getUserName())));
            //返回用户信息
            resultData.setData(JSON.toJSON(user));
            //设置返回结果状态
            System.out.println("user："+JSON.toJSON(user));
            userService.UpdateChannelId(channelId,user.getUserId());
            List<NewsEntity> getlinkdata=linkDataService.getData();
            cn.edu.fjnu.utils.BaiduPush.pushEctivity(getlinkdata.get(1),user);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        } catch (PushClientException e) {
            e.printStackTrace();
        } catch (PushServerException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(resultData, true));
        return JSON.toJSONString(resultData, true);

    }

    /**
     * 注册
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(@RequestParam(value = "user") String user) {
        ResultData resultData = new ResultData();
        try {
            User saveUser = JSON.parseObject(user, User.class);
           // String password = saveUser.getUserPwd().substring(saveUser.getUserPwd().length() - 6, saveUser.getUserPwd().length());
            saveUser.setUserPwd(Md5.digest(saveUser.getUserPwd().getBytes()));
            saveUser.setCreateTime(new Date());
            userService.saveUser(saveUser);
            //注册成功后创建accesstoken
            String as = loginLogService.createAccesstoken(saveUser.getPhoneNum());
            //创建客户端配置
            //configService.createConfig(saveUser.getPhoneNum());
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
     * 用户信息完善或者修改
     * 前端一定要有的内容：userId
     */
    @ResponseBody
    @RequestMapping(value = "/perfectormodify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String perfectormodify(@RequestParam(value = "user") String user,
                                  @RequestParam(value = "accesstoken")String accesstoken) throws IOException {
        ResultData resultData = new ResultData();
        try {
            User perfectUser = JSON.parseObject(user, User.class);
            User user1 =userService.getUserByAccesstoken(accesstoken);
            userService.updateUser(perfectUser,user1.getUserId());
            User getuser=userService.getUserInformation(user1.getUserId());
           // resultData.setData(getuser);
            resultData.setData(JSON.toJSON(getuser));
            resultData.setStatus(ResultData.SUCCESS);
        }
        catch (AppRTException e) {
                resultData.setStatus(ResultData.ERROR);
                resultData.setErrorCode(e.getCode());
                resultData.setData(e.getMessage());
                e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }

    /**
     * 获取用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/getinformation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getInformation(@RequestParam(value = "accesstoken")String accesstoken) throws IOException {
        ResultData resultData = new ResultData();
        try {
            User user1 = userService.getUserByAccesstoken(accesstoken);
            //if(user1.getUserHeadPicture()==null ||user1.getUserHeadPicture()=="")
              //  user1.setUserHeadPicture("http://zqhstep.applinzi.com/HeadPicture/120161220074002.png");
            User user=userService.getUserInformation(user1.getUserId());
            System.out.println(user);
            resultData.setData(JSON.toJSONString(user));
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
     * 用户头像上传（判断一下是否已有头像）
     */
    @ResponseBody
    @RequestMapping(value = "/modifyHeadPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String modifyHeadPicture(HttpServletRequest request,
                                    @RequestParam(value = "base64String") String base64String,
                                    @RequestParam(value = "accesstoken")String accesstoken) throws IOException {
        ResultData resultData = new ResultData();
        try{
            User user =userService.getUserByAccesstoken(accesstoken);
            String savePath =request.getServletContext().getRealPath("/HeadPicture/");
            userService.updateHeadPicture(base64String,user.getUserId(),savePath);
            //synamicService.updateHeadPicture(user.getUserHeadPicture(),user.getUserId());
            resultData.setStatus(ResultData.SUCCESS);
        }
        catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);

    }

    /**
     * 忘记密码
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String forgetPwd(@RequestParam(value = "newPwd") String newPwd,
                            @RequestParam(value = "phoneNum")String phoneNum) throws IOException {
        ResultData resultData = new ResultData();
        try{
            User user =userService.getUserByPhoneNum(phoneNum);
            String n =Md5.digest(newPwd.getBytes());
            userService.forgetPwd(n,user.getUserId());
            resultData.setStatus(ResultData.SUCCESS);
        }
        catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }

    /**
     * 修改密码
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String forgetPwd(@RequestParam(value = "newPwd") String newPwd,
                            @RequestParam(value = "oldPwd") String oldPwd,
                            @RequestParam(value = "accesstoken")String accesstoken) throws IOException {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            //saveUser.setUserPwd(Md5.digest(saveUser.getUserPwd().getBytes()));
            String n =Md5.digest(newPwd.getBytes());
            String o=Md5.digest(oldPwd.getBytes());
            userService.modifyPwd(n, o, user);
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
     * 用户信息完善或者修改
     * 前端一定要有的内容：phoneNum和userPwd
     */


}