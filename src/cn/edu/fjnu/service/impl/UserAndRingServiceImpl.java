package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.UserAndRing;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.UserAndRingDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.UserAndRingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by HMH on 2016/11/26.
 */
@Service
public class UserAndRingServiceImpl implements UserAndRingService{
    private Logger logger= LoggerFactory.getLogger(UserAndRingServiceImpl.class);

    @Resource
    private UserAndRingDao userAndRingDao;


    @Override
    public void saveRing(Synamic synamic, String thumbNo, String reviewerNo){
        if(synamic ==null){
            logger.info("Runningring is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "动态为空，保存失败");

        }
        UserAndRing userAndRing=new UserAndRing();
        userAndRing.setCreatorNo(synamic.getCreatorNo());
        userAndRing.setReviererNo(reviewerNo);
        userAndRing.setThumnNo(thumbNo);
        userAndRing.setRingNo(synamic.getRingId());
        System.out.println(synamic.getCreatorNo());
        System.out.println(reviewerNo);
        System.out.println(thumbNo);
        System.out.println(synamic.getRingId());
    }

    @Override
    public void deleteRing(Integer ruNo){
        if(ruNo==null){
            logger.info("deleteRing |ruNo is null");
            throw new AppRTException(AppExCode.AC_DELETE_ERROR,"删除动态失败");

        }
        System.out.println("要删除的动态号："+ruNo);
        userAndRingDao.deleteByRingNo(ruNo);
    }
}
