package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.*;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {

    private Logger logger = LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService positionService;

    @Resource
    private UserService userService;

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
            saveposition.setMonitoredNo(user.getUserId().toString());
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
            System.out.println(searchactivities.getActNo());
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
    * 获取活动对象固定时间内的轨迹
    * */
/*    @ResponseBody
    @RequestMapping(value = "/monitored/timePositon",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getTimePostion(@RequestParam(value = "monitoredNo")String monitoredNo,
                                 @RequestParam(value = "timeRange") String timeRange){
        ResultData resultData = new ResultData();
        try{
            List<Position> positionsList = positionService.getPositionRange(monitoredNo,timeRange);
            resultData.setData(JSON.toJSONString(positionsList));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }*/

}
