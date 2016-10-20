/*
package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.dao.ActivitiesDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.service.ActivitiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

*/
/**
 * @Author: linqiu
 * @Date: 2016/3/8 23:03
 * @Description:
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ActivitiesServiceTest {

    private Logger logger = LoggerFactory.getLogger(ActivitiesServiceTest.class);

    @Resource
    private ActivitiesService activitiesService;

    @Resource
    private ActivitiesDao activitiesDao;

    @Test
    public void createActivitiesTest(){
        Activities activities = new Activities();
        activities.setActName("晚点");
        activities.setDescription("知名晚点");
        activities.setAddrLat(231.12321);
        activities.setAddrLong(543.231);
        activities.setAddress("闽侯青口");
        activities.setActStatus(Activities.ActStatus.VALID);
        activities.setCreateTime(new Date());
        activities.setAplyLowlmt(new Date());
        activities.setAplyUpplmt(new Date());
        activities.setCreatorNo("1");
        activities.setCreateName("杜晨宇");
        activitiesService.createActivity(activities);
    }

    @Test
    public void getAllByCreatorNo(){
        Page page = new Page(2,2);
        List<Activities> activitiesList = activitiesService.getALLActivitiesByMonitor("1",false,page);
        System.out.println(activitiesList.size());
        System.out.println(activitiesList.get(0).getAddress());
    }

    @Test
    public void getActivityByMonitored(){
        Page page = new Page(1,2);
        List activitiesList = activitiesDao.getActivityByMonitoredOnTime("26",page);
        System.out.println(((Activities)activitiesList.get(0)).getAddress());
    }

    @Test
    public void getActivityByMonitoredService(){
        Page page = new Page(1,10);
        List<Activities> activitiesList = activitiesService.getAllActivitiesByMonitored("29",false,page);
        System.out.println(activitiesList.size());
        System.out.println(((Activities)activitiesList.get(0)).getAddress());
    }

    @Test
    public void getActivityByMonitorService(){
        Page page = new Page(1,10);
        List<Activities> activitiesList = activitiesService.getALLActivitiesByMonitor("1",false,page);
        System.out.println(activitiesList.size());
        System.out.println(((Activities)activitiesList.get(0)).getAddress());
    }
}
*/
