package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ActivitiesService;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.service.MonitoredService;
import com.alibaba.fastjson.JSON;
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
import java.util.List;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 12:29
 * @Description:
 */
@Controller
@RequestMapping(value = "activities")
public class ActicitiesController {

    private Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Resource
    private ActivitiesService activitiesService;

    @Resource
    private MonitoredService monitoredService;

    @Resource
    private MonitorService monitorService;

    @ResponseBody
    @RequestMapping(value = "/monitor/avtivities/saveorupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveActivities(@RequestParam(value = "actitivies") String activities,
                                 @RequestParam(value = "accesstoken") String accesstoken) {
        //输出测试
        System.out.println(activities);
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据校验
            Activities saveActivities = JSON.parseObject(activities, Activities.class);
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            saveActivities.setCreatorNo(monitor.getMonitorNo().toString());
            saveActivities.setCreateName(monitor.getRealName());

            activitiesService.createActivity(saveActivities);
            //获取所有被推送对象
            List<Monitored> monitoredList = monitorService.getMonitoredByMonitor(monitor.getMonitorNo(),saveActivities.getPushObject());
            //进行推送
            /**
             * 测试推送对象
             * */
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
        }
        return JSON.toJSONString(resultData, true);
    }


    @ResponseBody
    @RequestMapping(value = "/monitor/avtivities/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitor(@RequestParam(value = "accesstoken") String accesstoken,
                                         @RequestParam(value = "isOnTime") String isOnTime,
                                         @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            boolean timeType = JSONObject.parseObject(isOnTime, boolean.class);
            Page p = JSONObject.parseObject(page, Page.class);
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            List<Activities> activitiesList = activitiesService
                    .getALLActivitiesByMonitor(monitor.getMonitorNo().toString(), timeType, p);
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

    @ResponseBody
    @RequestMapping(value = "/monitored/avtivities/gett", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitored(@RequestParam(value = "accesstoken") String accesstoken,
                                           @RequestParam(value = "isOnTime") String isOnTime,
                                           @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            boolean timeType = JSONObject.parseObject(isOnTime, boolean.class);
            Page p = JSONObject.parseObject(page, Page.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            List<Activities> activitiesList = activitiesService
                    .getAllActivitiesByMonitored(monitored.getMonitoredNo().toString(), timeType, p);
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

    @ResponseBody
    @RequestMapping(value = "/monitor/avtivities/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getActivitiedByMonitored(@RequestParam(value = "accesstoken") String accesstoken,
                                           @RequestParam(value = "activities") String activities) {
        ResultData resultData = new ResultData();
        try {
            System.out.println(activities);
            Activities act = JSONObject.parseObject(activities, Activities.class);
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            System.out.println("登录令牌："+accesstoken);
            activitiesService.deleteActivities(act,monitor);
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
