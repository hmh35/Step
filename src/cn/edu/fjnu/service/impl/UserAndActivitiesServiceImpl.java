package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.UserAndActivities;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.UserAndActivitiesDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.UserAndActivitiesService;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */
@Service
public class UserAndActivitiesServiceImpl implements UserAndActivitiesService{
    private Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private UserAndActivitiesDao userAndActivitiesDao;

    @Override
    public void saveAct(Activities activities,String monitoredNo) {
        if(activities == null){
            logger.info("activities is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "活动为空，保存失败");
        }
        UserAndActivities userAndActivities = new UserAndActivities();
        userAndActivities.setCreatorNo(activities.getCreatorNo());
        userAndActivities.setMonitoredNo(monitoredNo);
        userAndActivities.setActNo(activities.getActNo());
        System.out.println(activities.getCreatorNo());
        System.out.println(monitoredNo);
        System.out.println(activities.getActNo());
        userAndActivitiesDao.saveOrUpdate(userAndActivities);
    }

    @Override
    public void deleteAct(Integer actNo) {
        if(actNo == null){
            logger.info("deleteAct | actNo is null");
            throw new AppRTException(AppExCode.AC_DELETE_ERROR,"删除活动失败");
        }
        System.out.println("要删除的活动号："+actNo);
        userAndActivitiesDao.deleteByActNo(actNo);
    }
}
