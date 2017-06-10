package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Thumb;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.LoginLogDao;
import cn.edu.fjnu.dao.ThumbDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ThumbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HMH on 2016/12/3.
 */
@Service
public class ThumbServiceImpl implements ThumbService{
    private Logger logger = LoggerFactory.getLogger(ThumbServiceImpl.class);

    @Resource
    private LoginLogDao loginLogDao;

    @Resource
    private ThumbDao thumbDao;

    @Override
    public Thumb getMyThumb(String ringId) {
        if (ringId == null || ringId == "") {
            logger.info("getMyThumb || this ringId is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "SynamicId为空，无法获得信息");
        }
        Thumb thumbList=thumbDao.getMyThumbByRingId(ringId);
        return thumbList;
    }

//创建新的点赞
    @Override
    public Thumb createThumb(Thumb thumb) {
        judge(thumb.getSynamicId(),thumb.getThumberId());
        if(thumb ==null ||thumb.getSynamicId()==null
                ||thumb.getThumberId()==null||thumb.getThumberId()==""){
            logger.info("createThumb | this thumb is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "点赞数据出错，无法点赞");
        }
        thumbDao.save(thumb);
        Thumb thumb1 =thumbDao.getMyThumbByRingId(thumb.getSynamicId().toString());
        return  thumb1;//返回的是所有点赞的数据

    }

    @Override
    public List getAllThumb(String ringId) {
        List<Thumb> thumbList=thumbDao.getMyThumb(ringId);
        return thumbList;
    }

    @Override
    public int getThumb(String ringId) {
        if (ringId == null || ringId == "") {
            logger.info("getMyThumb || this ringId is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "SynamicId为空，无法获得信息");
        }
        List<Thumb> thumbList=thumbDao.getMyThumb(ringId);
        return thumbList.size();
    }

    @Override
    public int countThumb(String ringId) {
        if (ringId == null || ringId == "") {
            logger.info("countThumb || this ringId is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "SynamicId为空，无法获得信息");
        }
        List thumbList=thumbDao.countThumb(ringId);
        return thumbList.size();
    }


    @Override
    public boolean judge(Integer ringId, String thumberId) {
        int number=thumbDao.judge(ringId, thumberId);
        if(number>0) {
            logger.info("judge || this thumb exists");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "您已经点赞过");
        }else
            return true;
    }

}
