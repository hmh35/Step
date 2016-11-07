package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.*;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import net.sf.json.JSONString;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.transform.Result;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/8 15:00
 * @Description:
 */
@Controller
@RequestMapping("/position")
public class PositionController {

    private Logger logger = LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService positionService;

    @Resource
    private UserService userService;

    @Resource
    private ContactsService contactsService;

    @Resource
    private MonitorService monitorService;

    @Resource
    private ActivitiesService activitiesService;

    /**
     * 保存地理位置（完成）
     * @param position
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String savePosition(@RequestParam(value = "position") String position,
                               @RequestParam(value = "accesstoken") String accesstoken) {
        Position saveposition = JSON.parseObject(position,Position.class);
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            saveposition.setUserId(user.getUserId().toString());
            saveposition.setCreateTime(new Date());
            positionService.savePosition(saveposition);
            //进行安全预警
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 被监护人获取自己的最新地理位置（完成）
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/newest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getNewestPosition(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            Position position = positionService.getNewestPosition(user.getUserId().toString());
            resultData.setData(JSON.toJSON(position));
            resultData.setStatus(ResultData.SUCCESS);
            System.out.println(position);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
    /**
     * 获取活动参与人员最新的位置信息（完成）
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/newest/joinner", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAlljoinnerNewestPosition(@RequestParam(value = "accesstoken") String accesstoken,
                                              @RequestParam(value = "activities") String activities) {
        ResultData resultData = new ResultData();
        try{
           // System.out.println("活动号："+actNo);
            Activities searchactivities = JSON.parseObject(activities,Activities.class);
            System.out.println("活动号"+searchactivities.getActNo());
            User user = userService.getUserByAccesstoken(accesstoken);
            List<Position> positions = positionService.getActivitiesObjectNewestPosition(user.getUserId().toString(),searchactivities.getActNo());
            System.out.println("参与者最新地理位置："+JSON.toJSONString(positions));//输出测试
            resultData.setData(JSON.toJSONString(positions));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取监护人手下的所有被监护人最新地理位置（完成）
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/newest/all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllNewestPosition(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try{
            User user = userService.getUserByAccesstoken(accesstoken);
            List<Position> positions = positionService.getNewestAll(user.getUserId().toString());
            resultData.setData(JSON.toJSONString(positions));
            System.out.println("所有被监护人最新地理位置："+JSON.toJSONString(positions));//输出测试
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取被监护人的所有地理位置（完成）
     * @param monitoredNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllPosition(@RequestParam(value = "monitoredNo") String monitoredNo) {
        ResultData resultData = new ResultData();
        try{
            List<Position> positions = positionService.getAllPosition(monitoredNo);
            System.out.println(JSON.toJSONString(positions));
            resultData.setData(JSON.toJSONString(positions));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /*
    * 获取活动对象一定时间内的轨迹(活动创建人请求时间和活动开始时间)
    * */
    @ResponseBody
    @RequestMapping(value = "/monitored/timePosition",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getTimePostion(@RequestParam(value = "monitoredNo")String monitoredNo,
                                 @RequestParam(value = "activities") String activities) {
        ResultData resultData = new ResultData();
        try{
            Activities getactivities = JSON.parseObject(activities,Activities.class);
            System.out.println(getactivities);
            List<Position> positions = positionService.getOutActPositionByMonitoredNo(monitoredNo,getactivities.getActNo().toString());
            System.out.println(JSON.toJSONString(positions));
            resultData.setData(JSON.toJSONString(positions));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);

    }

    /*
    * 位置分享并推送
    * */
    @ResponseBody
    @RequestMapping(value = "/user/pushShareInfo",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getShareInfo(@RequestParam(value = "accesstoken")String accesstoken,
                                 @RequestParam(value = "monitoredandmonitor") String monitoredandmonitor,
                                 @RequestParam(value = "timeStart") Timestamp timeStart,
                                 @RequestParam(value = "timeStop") Timestamp timeStop){
        ResultData resultData = new ResultData();
        try{
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(monitoredandmonitor);
            List<MonitoredAndMonitor> monitorNolist = JSON.parseArray(monitoredandmonitor,MonitoredAndMonitor.class);
            System.out.println(monitorNolist);
            List<User> monitorList = new ArrayList<User>();
            for(int i=0;i<monitorNolist.size();i++){
                monitorList.add(userService.getPushObjectByuserId(monitorNolist.get(i).getMonitorUserId().toString()));
            }
            //进行推送
            System.out.println("被推送对象："+monitorList);
            cn.edu.fjnu.utils.BaiduPush.pushShareInfo(user,timeStart,timeStop,monitorList);
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

    /*
    * 获取位置分享用户固定时间内的轨迹
    * */
    @ResponseBody
    @RequestMapping(value = "/user/SharePosition",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getTimePostion(@RequestParam(value = "userId")String userId,
                                 @RequestParam(value = "timeStart") Timestamp timeStart,
                                 @RequestParam(value = "timeStop") Timestamp timeStop){
        ResultData resultData = new ResultData();
        try{
            List<Position> positionsList = positionService.getRangeSharePositionBy(userId,timeStart,timeStop);
            resultData.setData(JSON.toJSONString(positionsList));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

}
