package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.UserAndActivities;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface UserAndActivitiesService {
    void saveAct(Activities activities, String monitoredNo);
    void deleteAct(Integer actNo);
}
