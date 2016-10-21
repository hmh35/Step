package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ConfigService;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.utils.Md5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.transform.Result;


@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private ConfigService configService;

    @Resource
    private MonitorService monitorService;

    @Resource
    private MonitoredService monitoredService;
    /**
     * 获取监护人客户端系统设置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getconfigByMonitor(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try{
            Monitor monitor = monitorService.getMonitorByAccesstoken(accesstoken);
            Config config = configService.getConfig(monitor.getUserName());
            resultData.setData(JSON.toJSON(config));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取被监护人客户端系统设置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getconfigByMonitored(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try{
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            Config config = configService.getConfig(monitored.getStudentNo());
            resultData.setData(JSON.toJSON(config));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取被监护人客户端系统设置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getconfigByMonitored(@RequestParam(value = "accesstoken") String accesstoken,
                                       @RequestParam(value = "config") String config) {
        ResultData resultData = new ResultData();
        try{
            Config updateConfig = JSONObject.parseObject(config,Config.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            configService.updateConfig(updateConfig);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 监控端客户端用户信息设置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitor/information")
    public String setMonitorInformation(@RequestParam(value = "monitor")String monitor,
                                        @RequestParam(value = "accesstoken")String accesstoken){
        System.out.println(monitor);
        ResultData resultData = new ResultData();
        try {
            Monitor savemonitor = JSON.parseObject(monitor, Monitor.class);
            String password = savemonitor.getUserName().substring(savemonitor.getUserName().length() - 6, savemonitor.getUserName().length());
            savemonitor.setUserPwd(Md5.digest(password.getBytes()));
            savemonitor.setStatus(Monitored.MonitoredStatus.VALID);
            monitorService.updateMonitor(savemonitor);
            resultData.setData(JSON.toJSONString(savemonitor));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData,true);
    }

    /**
     * 被监控端客户端用户信息设置
     * @param accesstoken
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/monitored/information")
    public String setMonitoredInformation(@RequestParam(value = "monitored")String monitored,
                                        @RequestParam(value = "accesstoken")String accesstoken){
        System.out.println(monitored);
        ResultData resultData = new ResultData();
        try {
            Monitored savemonitored = JSON.parseObject(monitored, Monitored.class);
            String password = savemonitored.getStudentNo().substring(savemonitored.getStudentNo().length() - 6, savemonitored.getStudentNo().length());
            savemonitored.setPassword(Md5.digest(password.getBytes()));
            savemonitored.setStatus(Monitored.MonitoredStatus.VALID);
            monitoredService.updateMonitored(savemonitored);
            resultData.setData(JSON.toJSONString(savemonitored));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData,true);
    }

    /*
    * 紧急求救设置
    * */
    @ResponseBody
    @RequestMapping(value = "/monitored/setEmergency")
    public String setIsEmergency(@RequestParam(value="accesstoken")String accesstoken){
        System.out.println(accesstoken);
        ResultData resultData = new ResultData();
        try{

        }catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData);
    }
}
