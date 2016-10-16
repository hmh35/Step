package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/8 20:30
 * @Description:
 */
public interface ActivitiesService {

    /**
     * 创建活动
     *
     * @param activities
     */
    void createActivity(Activities activities);



   Activities getActivitiesById(Integer actNo);
    /**
     * 通过创建者No获取活动
     *
     * @param creatorNo
     * @return
     */
    Activities getActivityByMonitor(String creatorNo);

    /**
     * 通过创建者No获得其所有活动
     *
     * @param creatorNo
     * @param isOnTime  活动是否过期
     * @return
     */
    List<Activities> getALLActivitiesByMonitor(String creatorNo, boolean isOnTime, Page page);

    /**
     * 通过monitored获取其监护人给其创建的活动
     *
     * @param monitored
     * @param isOnTime  活动是否过期
     * @return
     */
    List<Activities> getAllActivitiesByMonitored(String monitored, boolean isOnTime, Page page);

    /**
     * 删除活动
     * @param activities
     * @param user
     */
    void deleteActivities(Activities activities, User user);

}
