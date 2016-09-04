package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ContactsService;
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
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 20:38
 * @Description:
 */
@Controller
@RequestMapping(value = "contacts")
public class ContactsController {

    private Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private ContactsService contactsService;

    @Resource
    private MonitoredService monitoredService;

    @ResponseBody
    @RequestMapping(value = "/monitored/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveContacts(@RequestParam(value = "contacts") String contacts,
                                 @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据
            Contacts saveContacts = JSON.parseObject(contacts,Contacts.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            saveContacts.setMonitoredNo(monitored.getMonitoredNo().toString());
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
    @RequestMapping(value = "/monitored/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAllContacts(@RequestParam(value = "accesstoken") String accesstoken,
                                 @RequestParam(value = "page") String page) {
        ResultData resultData = new ResultData();
        try {
            Page p = JSONObject.parseObject(page,Page.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            List<Contacts> contactsList = contactsService.getAllContacts(monitored.getMonitoredNo().toString(),p);
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
                                 @RequestParam(value = "contacts") String contacts) {
        ResultData resultData = new ResultData();
        try {
            Contacts con = JSONObject.parseObject(contacts,Contacts.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            contactsService.deleteContactsByMonitored(monitored.getMonitoredNo().toString(),con);
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
    public String updateContacts(@RequestParam(value = "contacts") String contacts,
                               @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            //获取客户端数据
            Contacts saveContacts = JSON.parseObject(contacts,Contacts.class);
            Monitored monitored = monitoredService.getMonitoredByAccesstoken(accesstoken);
            saveContacts.setMonitoredNo(monitored.getMonitoredNo().toString());
            contactsService.updateContactByMonitored(saveContacts);
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e){
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
}
