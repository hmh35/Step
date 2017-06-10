package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Comment;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.CommentService;
import cn.edu.fjnu.service.SynamicService;
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
import java.util.List;

/**
 * Created by HMH on 2016/12/5.
 */
@Controller
@RequestMapping(value="comment")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    @Resource
    private SynamicService synamicService;

    @ResponseBody
    @RequestMapping(value="/user/comment/save",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String saveRunningRing(@RequestParam(value = "comment") String comment,
                                  @RequestParam(value = "accesstoken") String accesstoken){
        System.out.println(comment);
        ResultData resultData=new ResultData();
        try {
            //获取客户端数据校验
            Comment saveComment = JSON.parseObject(comment, Comment.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user);
            saveComment.setObserverId(user.getUserId().toString());//评论者ID
            saveComment.setObserverName(user.getUserName());//评论者名字
            saveComment.setObserverHeadPicture(user.getUserHeadPicture());//评论者头像
            //saveComment.setCommentCount(commentService.CountComment(saveComment.getSynamicId())+1);
            //saveComment.setCommentCount(commentService.getAllComment(saveComment.getSynamicId()).size()+1);

            Comment getComment = commentService.createComment(saveComment);
            System.out.println(getComment);
            resultData.setStatus(ResultData.SUCCESS);
        }catch (Exception e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setData("请求失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
    /**
     * 刷新朋友圈的时候一起刷新评论
     *
     */
    @ResponseBody
    @RequestMapping(value="/user/comment/getcomment",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getAllComment(@RequestParam(value = "synamicId") Integer synamicId,
                              @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Comment> commentList =commentService.getAllComment(synamicId);//得到所有评论信息
            System.out.println(commentList);
            resultData.setData(JSON.toJSONString(commentList));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }

    @ResponseBody
    @RequestMapping(value="/user/comment/getcommentcount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getThumbCount(@RequestParam(value = "synamicId") Integer synamicId,
                              @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            User user = userService.getUserByAccesstoken(accesstoken);
            System.out.println(user.getUserId().toString());
            List<Comment> commentList =commentService.getAllComment(synamicId);//得到所有评论信息
            System.out.println(commentList);
            resultData.setData(JSON.toJSONString(commentList.size()));
            resultData.setStatus(ResultData.SUCCESS);
        }catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(e.getCode());
            resultData.setData(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toJSONString(resultData, true);
    }
    /**
     * 删除评论
     *
     */
    @ResponseBody
    @RequestMapping(value="/user/comment/deletecomment",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getAllThumb(@RequestParam(value = "comment") String comment,
                              @RequestParam(value = "accesstoken") String accesstoken) {
        ResultData resultData = new ResultData();
        try {
            System.out.println(comment);
            Comment com = JSONObject.parseObject(comment, Comment.class);
            User user = userService.getUserByAccesstoken(accesstoken);
            commentService.deleteComment(com, user);
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
