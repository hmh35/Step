package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ContactsService;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.service.UserService;
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
import java.util.List;


@Controller
@RequestMapping(value = "contacts")
public class ContactsController {

    private Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private ContactsService contactsService;

    @Resource
    private UserService userService;



    @ResponseBody
    @RequestMapping(value = "/monitored/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveContacts(@RequestParam(value = "contact") String contact,
                               @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据
            MonitoredAndMonitor saveContacts=JSON.parseObject(contact,MonitoredAndMonitor.class);
            //通过联系人的电话号码判断是不是以注册用户

            User monitor=userService.getUserByPhoneNum(saveContacts.getMonitorPhone());
            if(monitor!=null)
            {
                saveContacts.setMonitorUserId(monitor.getUserId());
            }
            User monitored = userService.getUserByAccesstoken(accesstoken);
            saveContacts.setMonitoredNo(monitored.getUserId().toString());
            saveContacts.setCreateTime(new Date());
            contactsService.createContacts(saveContacts);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }


    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllContacts(@RequestParam(value = "accesstoken") String accesstoken,
                                 @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            Page p = JSONObject.parseObject(page,Page.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            List<MonitoredAndMonitor> contactsList = contactsService.getAllContacts(user.getUserId().toString(),p);
            resultData.setData(JSON.toJSONString(contactsList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    @ResponseBody
    @RequestMapping(value = "/monitored/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteContacts(@RequestParam(value = "accesstoken") String accesstoken,
                                 @RequestParam(value = "contact") String contact) {
        ResultData resultData = new ResultData();
        try {
            //User monitor=userService.getUserByPhoneNum(phoneNum);
            MonitoredAndMonitor deleteContacts=JSON.parseObject(contact,MonitoredAndMonitor.class);
            User monitored = userService.getUserByAccesstoken(accesstoken);
            contactsService.deleteContactsByMonitoredAndMonitor(monitored.getUserId(),deleteContacts);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    @ResponseBody
    @RequestMapping(value = "/monitored/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateContacts(@RequestParam(value = "contact") String contact,
                                 @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据
            MonitoredAndMonitor saveContacts = JSON.parseObject(contact, MonitoredAndMonitor.class);
            //通过联系人的电话号码判断是不是以注册用

            User monitor = userService.getUserByPhoneNum(saveContacts.getMonitorPhone());
            if (monitor != null) {
                saveContacts.setMonitorUserId(monitor.getUserId());
            }
            User monitored = userService.getUserByAccesstoken(accesstoken);
            saveContacts.setMonitoredNo(monitored.getUserId().toString());
            saveContacts.setUpdateTime(new Date());
            contactsService.updateContacts(saveContacts);
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
    * 获取推送对象
    * */
    @ResponseBody
    @RequestMapping(value = "/pushuser/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllContacts(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        System.out.println(accesstoken);
        try {
            System.out.println(accesstoken);
            User user = userService.getUserByAccesstoken(accesstoken);
            if(user!=null) {
                List<MonitoredAndMonitor> contactsList = contactsService.getProContacts(user.getUserId().toString());
                System.out.println(contactsList);
                resultData.setData(JSON.toJSONString(contactsList));
                resultData.setStatus(ResultData.SUCCESS);
            }
            else{
                List<MonitoredAndMonitor> contactsList = null;
                resultData.setData(JSON.toJSONString(contactsList));
                resultData.setStatus(ResultData.ERROR);
            }

        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
}