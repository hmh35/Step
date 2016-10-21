package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.*;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "activities")
public class ActivitiesController {

    private Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Resource
    private ActivitiesService activitiesService;

    @Resource
    private MonitoredService monitoredService;

    @Resource
    private MonitorService monitorService;

    @Resource
    private UserService userService;

    @Resource
    private UserAndActivitiesService userAndActivitiesService;

    @Resource
    private MonitoredAndMonitorService monitoredAndMonitorService;

    /*
    * 创建活动
    * */
    @ResponseBody
    @RequestMapping(value = "/user/activities/saveorupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveActivities(@RequestParam(value = "activities") String activities,
                                 @RequestParam(value = "accesstoken") String accesstoken,
                                 @RequestParam(value = "monitoredandmonitor") String monitoredandmonitor) {
        //输出测试
        System.out.println(activities);
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据校验
            Activities saveActivities = JSON.parseObject(activities, Activities.class);
            System.out.println(monitoredandmonitor);
            List<MonitoredAndMonitor> monitoredNolist = JSON.parseArray(monitoredandmonitor,MonitoredAndMonitor.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            saveActivities.setCreatorNo(user.getUserId().toString());
            saveActivities.setCreateName(user.getRealName());
            System.out.println(user.getUserId());
            Activities getactivities = activitiesService.createActivity(saveActivities);
            //获取所有被推送对象   未处理用户Id不存在的情况
            //List<User> monitoredList = userService.getMonitoredByMonitor(monitoredNolist);
            List<User> monitoredList=new ArrayList<User>();
            for(int i = 0;i<monitoredNolist.size();i++) {
                userAndActivitiesService.saveAct(getactivities, monitoredNolist.get(i).getMonitoredNo().toString());
                monitoredList.add(userService.getPushObjectByuserId(monitoredNolist.get(i).getMonitoredNo().toString()));
            }
            System.out.println(saveActivities);
            //进行推送
            System.out.println("被推送对象："+monitoredList);
            cn.edu.fjnu.utils.BaiduPush.pushActivity(saveActivities, monitoredList);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (PushClientException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(AppExCode.PUSH_ERROR);
            resultData.setData("推送请求失败");
            e.printStackTrace();
        } catch (PushServerException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(AppExCode.PUSH_ERROR);
            resultData.setData("推送请求失败");
            e.printStackTrace();
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            //查找不到对应的被监护人，也属于推送失败
            if (e.getCode().equals(AppExCode.U_NOT_FIND_MONITORED)) {
                resultData.setErrorCode(AppExCode.PUSH_ERROR);
                resultData.setData("推送请求失败");
            } else {
                resultData.setErrorCode(e.getCode());
                resultData.setData(e.getMessage());
            }
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /*
    * 获取我关注的活动
    * */
    @ResponseBody
    @RequestMapping(value = "/monitor/activities/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitor(@RequestParam(value = "accesstoken") String accesstoken,
                                         @RequestParam(value = "isOnTime") String isOnTime,
                                         @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            boolean timeType = JSONObject.parseObject(isOnTime, boolean.class);
            Page p = JSONObject.parseObject(page, Page.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            List<Activities> activitiesList = activitiesService.getALLActivitiesByMonitor(user.getUserId().toString(), timeType, p);
            resultData.setData(JSON.toJSONString(activitiesList));
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

    /*
    * 获取我被关注的活动
    * */
    @ResponseBody
    @RequestMapping(value = "/monitored/activities/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitored(@RequestParam(value = "accesstoken") String accesstoken,
                                           @RequestParam(value = "isOnTime") String isOnTime,
                                           @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        System.out.println(page);
        try {
            boolean timeType = JSONObject.parseObject(isOnTime, boolean.class);
            Page p = JSONObject.parseObject(page, Page.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Activities> activitiesList = activitiesService
                    .getAllActivitiesByMonitored(user.getUserId().toString(), timeType, p);
            System.out.println(activitiesList);
            resultData.setData(JSON.toJSONString(activitiesList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /*
    * 删除我关注的活动
    * */
    @ResponseBody
    @RequestMapping(value = "/monitor/activities/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitored(@RequestParam(value = "accesstoken") String accesstoken,
                                           @RequestParam(value = "activities") String activities) {
        ResultData resultData = new ResultData();
        try {
            System.out.println(activities);
            Activities act = JSONObject.parseObject(activities, Activities.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println("登录令牌："+accesstoken);
            activitiesService.deleteActivities(act,user);
            userAndActivitiesService.deleteAct(act.getActNo());
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
}
