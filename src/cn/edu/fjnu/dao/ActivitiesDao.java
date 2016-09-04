package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.dao.base.GenericDao;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/8 20:29
 * @Description:
 */
public interface ActivitiesDao extends GenericDao<Activities, Integer> {

    /**
     * 获取未过期的活动
     *
     * @param monitoredNo
     * @return
     */
    List getActivityByMonitoredOnTime(String monitoredNo, Page page);

    /**
     * 获取已过期的活动
     *
     * @param monitoredNo
     * @return
     */
    List getActivityByMonitoredOutTime(String monitoredNo, Page page);

    /**
     * 通过创建者获取活动
     *
     * @param monitorNo
     * @return
     */
    List getActivityByMonitor(String monitorNo, boolean isOnTime, Page page);

}
