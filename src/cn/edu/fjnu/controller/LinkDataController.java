package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LinkDataService;
import cn.edu.fjnu.service.SynamicService;
import cn.edu.fjnu.service.UserService;
import cn.edu.fjnu.utils.BaiduPush;
import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMH on 2016/12/10.
 */
@Controller
@RequestMapping(value="newsentity")
public class LinkDataController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private LinkDataService linkDataService;

    @Resource
    private SynamicService synamicService;


    @ResponseBody
    @RequestMapping(value="/get",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String saveRunningRing(@RequestParam(value = "accesstoken") String accesstoken) throws PushClientException, PushServerException {
        ResultData resultData=new ResultData();
        try{
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
           // List<NewsEntity> getlinkdata =linkDataService.getLinkData();//爬虫
           // linkDataService.saveLinkData(getlinkdata);
            List<NewsEntity> getlinkdata1=linkDataService.getData();//从数据库读取

            //List<User> monitoredList=new ArrayList<User>();//推送
            //for(int i = 0;i<getlinkdata.size();i++) {
           // for(int i =0;i<monitoredList.size();i++){
             //   monitoredList.add(userService.getPushObjectByuserId(user.getUserId().toString()));
               // BaiduPush.pushEctivity(getlinkdata.get(1),monitoredList);
            //BaiduPush.pushEctivity(getlinkdata.get(1),user);
            //cn.edu.fjnu.utils.BaiduPush.pushEctivity(getlinkdata.get(1),user);
            //}
            resultData.setData(JSON.toJSONString(getlinkdata1));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
}
