package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.service.PositionService;
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
    private MonitoredService monitoredService;

    @Resource
    private MonitorService monitorService;

    /**
     * 保存地理位置
     * @param position
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String savePosition(@RequestParam(value = "position") String position,
                               @RequestParam(value = "accesstoken") String accesstoken) {
        Position savePosition = JSON.parseObject(position,Position.class);
        ResultData resultData = new ResultData();
        try {
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            savePosition.setMonitoredNo(monitored.getMonitoredNo().toString());
            positionService.savePosition(savePosition);
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
     * 获取某个被监护人最新地理位置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/newest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getNewestPosition(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            Position position = positionService.getNewestPosition(monitored.getMonitoredNo().toString());
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
     * 获取监护人手下的所有被监护人最新地理位置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/newest/all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllNewestPosition(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try{
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            List<Position> positions = positionService.getNewestAll(monitor.getMonitorNo().toString());
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
     * 获取获取被监护人的所有地理位置
     * @param monitoredNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllPosition(@RequestParam(value = "monitoredNo") String monitoredNo) {
        ResultData resultData = new ResultData();
        try{
            List<Position> positions = positionService.getAllPosition(monitoredNo);
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
}
