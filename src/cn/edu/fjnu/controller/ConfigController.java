package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
//import cn.edu.fjnu.beans.TestData;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.dao.ConfigDao;
//import cn.edu.fjnu.dao.TestDataDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ConfigService;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.service.MonitoredService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:32
 * @Description:
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(ConfigController.class);
   // @Resource
    //private TestDataDao testDataDao;

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
     *
     */
    @RequestMapping(value = "/savedata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void savedata(@RequestParam(value = "testdata") String testdata) {
       /* TestData  testData1=new TestData();
        testData1.setName("王鑫");
        testData1.setCreateTime(new Date());
        testData1.setSex("男");
        testData1.setSpeedRate("32-36.3");
        testData1.setAge(22);
        testData1.setHeartRate("32-36");
        String speedax="00000000";
        for(int i=0;i<3000;i++)
            speedax=speedax+"-"+"111111111111111111";
        speedax=speedax+"-"+"000000000000";
        testData1.setSpeedAx(speedax);
        testData1.setSpeedAy("123");
        testData1.setSpeedAz("123");
        testData1.setHeight(177.00);
        testData1.setWeight(60.00);*/
        /*String testdata=JSON.toJSONString(testData1, true);*/
        System.out.println("测试数据:"+testdata);
       // TestData testData = JSONObject.parseObject(testdata,TestData.class);
     //   if(testData.getName()==""||testData.getSpeedRate()==""||testData.getSpeedRate()==null||testData.getName()==null)
       //     return;
      //  testData.setCreateTime(new Date());
      //  testDataDao.save(testData);
      //  System.out.println("保存成功");
    }

}
