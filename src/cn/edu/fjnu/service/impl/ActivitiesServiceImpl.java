package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.UserAndActivities;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ActivitiesDao;
import cn.edu.fjnu.dao.UserAndActivitiesDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ActivitiesService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/8 20:31
 * @Description:
 */
@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private Logger logger = LoggerFactory.getLogger(ActivitiesServiceImpl.class);

    @Resource
    private ActivitiesDao activitiesDao;

    @Override
    public Activities createActivity(Activities activities) throws ParseException {
        if (activities == null
                || activities.getCreatorNo() == null || activities.getCreatorNo() == ""
                || activities.getActName() == null || activities.getActName() == ""
                || activities.getAplyUpplmt() == null || activities.getAplyLowlmt() == null
                || activities.getAddrLat() == null || activities.getAddrLong() == null
                || activities.getAddress() == null || activities.getAddress() == "") {
            logger.info("createActivity | this activities is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "活动数据出错，无法创建新活动");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(activities.getActNo()!=null) {
          //activities.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            Date date = new Date();
            String dt = sdf.format(date);
            activities.setUpdateTime(date);
            activities.setTimeLab(dt);
        }
        else {
            Date date = new Date();
            String dt = sdf.format(date);
            System.out.println(dt);
            activities.setCreateTime(date);
            activities.setTimeLab(dt);
            System.out.println(activities.getCreateTime());
        }
        activities.setActStatus(Activities.ActStatus.VALID);
        //保存活动
        activitiesDao.saveOrUpdate(activities);
        Activities activities1 = activitiesDao.getActivitiesByCCT(activities.getCreatorNo(),activities.getTimeLab());
        return activities1;
    }


    @Override
    public Activities getActivityByMonitor(String creatorNo) {
        if (creatorNo == null || creatorNo == "") {
            logger.info("getActivityByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得活动");
        }
        Activities activities = activitiesDao.uniqueResult("creatorNo", creatorNo);
        if (activities == null) {
            logger.info("getActivityByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得活动");
        }
        return activities;
    }

    @Override
    public List<Activities> getALLActivitiesByMonitor(String creatorNo, boolean isOnTime, Page page) {
        if (creatorNo == null || creatorNo == "") {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "创建者编号为空，无法获得活动");
        }
        List<Activities> activitiesList;
        System.out.println("调用监控端");
        if (isOnTime) {
            activitiesList = activitiesDao.getActivityByMonitor(creatorNo, isOnTime, page);
        } else {
            activitiesList = activitiesDao.getActivityByMonitor(creatorNo, isOnTime, page);
        }
        if (activitiesList == null || activitiesList.size() == 0) {
            logger.info("getALLActivitiesByMonitor || this createNo is null");
            throw new AppRTException(AppExCode.AC_NOT_FOUND, "不存在属于监护人的活动");
        }
        return activitiesList;
    }


    @Override
    public List<Activities> getAllActivitiesByMonitored(String monitored, boolean isOnTime, Page page) {
        if (monitored == null || monitored == "") {
            logger.info("getAllActivitiesByMonitored || this createNo is null");
            throw new AppRTException(AppExCode.AC_PARA_NULL, "用户编号为空，无法获得活动");
        }
        List<Activities> activitiesList;
        System.out.println("调用被监控端");
        if (isOnTime) {
            activitiesList = activitiesDao.getActivityByMonitoredOnTime(monitored, page);
        } else {
            activitiesList = activitiesDao.getActivityByMonitoredOutTime(monitored, page);
        }
        if (activitiesList.size() == 0) {
            logger.info("getAllActivitiesByMonitored || the activities is null");
            throw new AppRTException(AppExCode.AC_NOT_FOUND, "不存在属于被监护人的活动");
        }
        return activitiesList;
    }

    @Override
    public void deleteActivities(Activities activities,User user){
        if (activities == null || user== null /*|| !(activities.getCreatorNo().equals(user.getUserNo().toString()))*/) {
            System.out.println("if 执行");
            logger.info("DeleteActivities || this activitiesNo is null");
            throw new AppRTException(AppExCode.AC_DELETE_ERROR, "删除活动失败");
        }
        System.out.println("要删除的活动号："+activities.getActNo());
        activitiesDao.deleteById(activities.getActNo());
    }
    @Override
    public Activities getActivitiesById(Integer actNo)
    {
        Activities activities=activitiesDao.findById(actNo);
        return activities;
    }
}
