package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Thumb;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.SynamicService;
import cn.edu.fjnu.service.ThumbService;
import cn.edu.fjnu.service.UserAndRingService;
import cn.edu.fjnu.service.UserService;
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
 * Created by HMH on 2016/12/6.
 */
@Controller
@RequestMapping(value="synamic")
public class ThumbController {

        private Logger logger = LoggerFactory.getLogger(UserController.class);

        @Resource
        private UserService userService;

        @Resource
        private SynamicService synamicService;

        @Resource
        private UserAndRingService userAndRingService;

        @Resource
        private ThumbService thumbService;
    /**
     * 点赞
     /* */
    @ResponseBody
    @RequestMapping(value="/user/thumb/thumb",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getThumb(@RequestParam(value = "thumb") String thumb,
                           @RequestParam(value = "accesstoken")String accesstoken) {
        System.out.println(thumb);
        ResultData resultData = new ResultData();
        try {
            Thumb saveThumb = JSON.parseObject(thumb,Thumb.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            saveThumb.setThumbHeadPicture(user.getUserHeadPicture());//点赞者头像
            saveThumb.setThumberId(user.getUserId().toString());//点赞者ID
            saveThumb.setThumbCount(thumbService.countThumb(saveThumb.getSynamicId().toString()));
            Thumb getThumb =thumbService.createThumb(saveThumb);
            System.out.println(getThumb);
            resultData.setStatus(ResultData.SUCCESS);
        }catch (Exception e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setData("请求失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    /**
     * 数信朋友圈的时候一起刷新点赞数
     *
     */
    @ResponseBody
    @RequestMapping(value="/user/thumb/getthumb",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getAllThumb(@RequestParam(value = "synamicId") Integer synamicId,
                           @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Thumb> thumbList =thumbService.getAllThumb(synamicId.toString());//得到所有点赞信息
            System.out.println(thumbList);
            resultData.setData(JSON.toJSONString(thumbList));
            //resultData.setData(JSON.toJSONString(thumbList.size()+1));
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
