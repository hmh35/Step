package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.UserAndActivities;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface UserAndActivitiesDao extends GenericDao<UserAndActivities,Integer> {
    void deleteByActNo(Integer actNo);
}
