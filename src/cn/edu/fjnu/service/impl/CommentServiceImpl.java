package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Comment;
import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.CommentDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.CommentService;
import cn.edu.fjnu.service.SynamicService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by HMH on 2016/12/5.
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentDao commentDao;

    @Resource
    private SynamicService synamicService;

    Logger logger =LoggerFactory.getLogger(CommentService.class);

    @Override
    public Comment createComment(Comment comment) {
        if(/*comment ==null||*/
                comment.getSynamicId()==null ||
                comment.getReceiverId()==null ||comment.getReceiverId()=="")
        {
            logger.info("createComment null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "评论信息不全，无法完成评论");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dt = sdf.format(date);
        System.out.println(dt);
        comment.setCommentTime(dt);//评论时间
        Synamic synamic = synamicService.getRunningRingByNo(comment.getSynamicId());
        //commentDao.setCmmentCount(comment.getSynamicId().toString(),CountComment(comment.getSynamicId()));
        commentDao.save(comment);
        //commentDao.update(comment);

       // synamic.setReviewId(comment.getCommentID());//评论数

        Comment comment1 =commentDao.getMyCommentByRingId(comment.getSynamicId().toString());
       return  comment1;
    }

     /*****算点赞数**/
    @Override
    public Integer CountComment(Integer synamicId) {
        List comment=commentDao.CountComment(synamicId);
        return comment.size();
    }

    @Override
    public List getAllComment(Integer ringId) {
        List<Comment> commentList=commentDao.CountComment(ringId);
        return commentList;
    }

    @Override
    public void deleteComment(Comment comment, User user) {
        if (comment == null || user== null) {
            System.out.println("if 执行");
            logger.info("DeleteActivities || this activitiesNo is null");
            throw new AppRTException(AppExCode.AC_DELETE_ERROR, "删除活动失败");
        }
        System.out.println("要删除的评论号："+comment.getCommentID());
       // activitiesDao.deleteById(activities.getActNo());
        commentDao.deleteById(comment.getCommentID());
    }
}
