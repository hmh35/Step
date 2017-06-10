package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.*;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMH on 2016/11/25.
 */
@Controller
@RequestMapping(value="synamic")
public class SynamicController extends HttpServlet{

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private SynamicService synamicService;

    @Resource
    private UserAndRingService userAndRingService;

    @Resource
    private ThumbService thumbService;

    @Resource
    private CommentService commentService;

    /**
     * 发布动态或者更新自己发布的动态
     */
    @ResponseBody
    @RequestMapping(value="/user/RunningRing/save",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String saveRunningRing(HttpServletRequest request,
                                  @RequestParam(value = "synamic") String synamic,
                                  @RequestParam(value = "accesstoken") String accesstoken){
       /* String s=request.getParameter("synamic");
        System.out.println("request的参数"+s);
        System.out.println(synamic);*/
        ResultData resultData=new ResultData();
        try{
            //获取客户端数据校验
            Synamic saveSynamic = JSON.parseObject(synamic,Synamic.class);
            User user=userService.getUserByAccesstoken(accesstoken);
            System.out.println(user);
            saveSynamic.setCreatorNo(user.getUserId().toString());
            saveSynamic.setCreatorName(user.getUserName());
            saveSynamic.setUserHeadPicture(user.getUserHeadPicture());
            //上传文件
            String savePath =request.getServletContext().getRealPath("/Picture/");
            System.out.println("9999"+savePath);
            Synamic getsynamic=synamicService.publishRunningRing(saveSynamic,savePath);

            System.out.println(getsynamic);
            System.out.println(user.getUserId());
            resultData.setStatus(ResultData.SUCCESS);
        }catch (Exception e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setData("请求失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取我的的动态
     * 根据Page
    /* */
    @ResponseBody
    @RequestMapping(value="/user/Synamic/refresh",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateSynamic(@RequestParam(value = "accesstoken") String accesstoken,
                                       @RequestParam(value = "page") String page) {
        ResultData resultData=new ResultData();
        System.out.println(page);
        try{
            Page p = JSONObject.parseObject(page, Page.class);
            User user=userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Synamic> synamicList=synamicService.getMySynamic(user.getUserId().toString(),p);
            System.out.println(synamicList);
            resultData.setData(JSON.toJSONString(synamicList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 获取我的的动态
     /* */
    @ResponseBody
    @RequestMapping(value="/user/Synamic/myrefresh",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateMySynamic(@RequestParam(value = "accesstoken") String accesstoken){
        ResultData resultData=new ResultData();
        try{
            User user=userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Synamic> synamicList=synamicService.getMySynamic(user.getUserId().toString());
            System.out.println(synamicList);
            resultData.setData(JSON.toJSONString(synamicList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
    /**
     * 获取我的的动态
     /* */
    @ResponseBody
    @RequestMapping(value="/user/Synamic/getAllSynamic",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getAllSynamic(@RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Synamic> synamicList = synamicService.getAllSynamic();
            System.out.println(synamicList);
            resultData.setData(JSON.toJSONString(synamicList));
            resultData.setStatus(ResultData.SUCCESS);
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 删除朋友圈
     *
     */
    @ResponseBody
    @RequestMapping(value="/user/Synamic/deleteSynamic",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getAllThumb(@RequestParam(value = "synamic") String synamic,
                              @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            System.out.println(synamic);
            Synamic sya =JSONObject.parseObject(synamic,Synamic.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            synamicService.deleteRunningRing(sya,user);
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
