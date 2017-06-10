package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.SynamicDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.CommentService;
import cn.edu.fjnu.service.SynamicService;
import cn.edu.fjnu.service.ThumbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * Created by HMH on 2016/11/27.
 */


/**
 * Created by HMH on 2016/11/25.
 */
@Service
public class SynamicServiceImpl implements SynamicService {

    private Logger logger = LoggerFactory.getLogger(SynamicServiceImpl.class);

    @Resource
    private SynamicDao synamicDao;

    @Resource
    private CommentService  commentService;

    @Resource
    private ThumbService thumbService;

    /**
     * 发布动态
     */
    @Override
    public Synamic publishRunningRing(Synamic synamic,String savepath) throws ParseException, IOException {
        if (synamic == null
                || synamic.getCreatorNo() == null || synamic.getCreatorNo() == "") {
            logger.info("publishRunningRing | this Synamic is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "活动数据出错，无法创建新活动");
        }
       // synamicService.ReturnPicture(saveSynamic.getPicture(),saveSynamic.getRingId());
        String path=ReturnPicture(synamic.getPicture(),synamic.getRingId(),savepath);
        synamic.setPicture(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dt = sdf.format(date);
        System.out.println(dt);
        synamic.setCreateTime(date);
        System.out.println(synamic.getCreateTime());
        synamic.setRingStatus(Synamic.ringStatus.VALID);

        synamicDao.save(synamic);
        Synamic synamic1 = synamicDao.getRunningRingByCCT(synamic.getCreatorNo(), synamic.getCreateTime().toString());
        return synamic1;
    }

    /**
     * 通过创建者ID获取活动
     */
    @Override
    public Synamic getRunningRingByCreator(String creatorNo,Page p) {
        if (creatorNo == null || creatorNo == "") {
            logger.info("getActivityByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得活动");
        }
        Synamic synamic = synamicDao.uniqueResult("creatorNo", creatorNo);
        if (synamic == null) {
            logger.info("getActivityByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得活动");
        }
        return synamic;
    }

    @Override
    public List<Synamic> getMySynamic(String creatorNo, Page p) {
        if (creatorNo == null || creatorNo == "") {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得信息");
        }
        List<Synamic> synamicList=synamicDao.getRunningRingByCreator(creatorNo,p);
        if ( synamicList== null || synamicList.size() == 0) {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_NOT_FOUND, "不存在属于用户的活动");
        }
        return synamicList;

    }

    @Override
    public List<Synamic> getMySynamic(String creatorNo) {
        if (creatorNo == null || creatorNo == "") {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得信息");
        }
        List<Synamic> synamicList=synamicDao.getSynamicByCreator(creatorNo);
         for(int i=0;i<synamicList.size();i++){
                Synamic Sy =synamicList.get(i);
               // Synamic synamic =getRunningRingByNo(Sy.getRingId());
                //synamic.setReviewId(commentService.CountComment(Sy.getRingId()));
                synamicDao.updateComment(commentService.CountComment(Sy.getRingId()),Sy.getRingId());
                synamicDao.updateThumb(thumbService.getThumb(Sy.getRingId().toString()),Sy.getRingId());
            }
       /* if ( synamicList== null || synamicList.size() == 0) {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_NOT_FOUND, "不存在属于用户的活动");
        }*/
        return synamicList;
    }

    @Override
    public List<Synamic> getAllSynamic() {
        List<Synamic> synamicList=synamicDao.getAllSynamic();
        for(int i=0;i<synamicList.size();i++){
            Synamic Sy =synamicList.get(i);
            // Synamic synamic =getRunningRingByNo(Sy.getRingId());
            //synamic.setReviewId(commentService.CountComment(Sy.getRingId()));
            synamicDao.updateComment(commentService.CountComment(Sy.getRingId()),Sy.getRingId());
            synamicDao.updateThumb(thumbService.getThumb(Sy.getRingId().toString()),Sy.getRingId());
        }
        return synamicList;
    }

    /**
     * 删除动态
     */
    @Override
    public void deleteRunningRing(Synamic synamic, User user) {
        if (synamic == null || user == null /*|| !(activities.getCreatorNo().equals(user.getUserNo().toString()))*/) {
            System.out.println("if 执行");
            logger.info("DeleteActivities || this activitiesNo is null");
            throw new AppRTException(AppExCode.AC_DELETE_ERROR, "删除活动失败");
        }
        System.out.println("要删除的活动号：" + synamic.getRingId());
        synamicDao.deleteById(synamic.getRingId());
    }

    /**
     * 获取指定动态号动态\并设置评论数,点赞数
     */
    @Override
    public Synamic getRunningRingByNo(Integer ringId) {
        Synamic synamic = synamicDao.findById(ringId);
       // synamic.setReviewId(commentService.CountComment(ringId));
       // synamic.setThumb(thumbService.countThumb(ringId.toString()));
        return synamic;
    }


      @Override
    public String ReturnPicture(String base64String, Integer ringId,String savePath) throws IOException {
          BASE64Decoder decoder = new BASE64Decoder();
          byte[] bytes1 = decoder.decodeBuffer(base64String);
          StringBuffer buf = new StringBuffer();

          buf.append(ringId);//加上朋友圈的编号
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
          Date date = new Date();
          String dt = sdf.format(date);
          buf.append(dt);//加上日期
          String filename=buf.toString();
          File tmpFile = new File(savePath);
          if (!tmpFile.exists()) {
              //创建临时目录
              tmpFile.mkdir();
          }
          File file=new File(savePath+filename+".png");
          FileOutputStream fos=new FileOutputStream(file);
          fos.write(bytes1);
          fos.close();
          System.out.println(file);
          String path=file.getPath();
         // String path2=path.replace(savePath,"http://zqhstep.applinzi.com/Picture/");
         // String path2=path.replace(savePath,"http://192.168.43.32:8080/Step/Picture/");
          String path2=path.replace(savePath,"http://114.215.99.158:8080/Step/Picture/");
         // String path3= path.replace("E://JAVA Work//Step//web//","http://192.168.43.32:8080/");
          return path2;
    }

    @Override
    public String ToImageBinary(Integer ringId) throws IOException {
        String ringpicture=synamicDao.getActNoRunningRing(ringId).getPicture();
        File file=new File(ringpicture);
        BASE64Encoder encoder = new BASE64Encoder();
        BufferedImage bi;
        bi=ImageIO.read(file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(bi,"jpg",baos);
        byte[] bytes =baos.toByteArray();
        return encoder.encodeBuffer(bytes).trim();
    }

    @Override
    public void updateHeadPicture(String path, Integer userId) {
        synamicDao.updateHeadPicture(path,userId.toString());
    }


}



