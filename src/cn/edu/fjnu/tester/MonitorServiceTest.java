/*
package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.MonitorDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.service.MonitorService;
import cn.edu.fjnu.utils.Md5;
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
 * @Date: 2016/3/6 19:34
 * @Description:
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MonitorServiceTest {
    private Logger logger = LoggerFactory.getLogger(MonitorServiceTest.class);

    @Resource
    private MonitorService monitorService;

    @Resource
    private MonitorDao monitorDao;

    @Test
    public void testSave(){
        Monitor monitor = new Monitor();
        monitor.setUserName("duchengyu1");
        monitor.setUserPwd(Md5.digest("123456".getBytes()));
        monitor.setRealName("杜成煜");
        monitor.setTitle("辅导员");
        monitor.setPhoneNumber("15659928899");
        monitor.setIdentityCardNo("35012119930203****");
        monitor.setStatus(Monitor.MonitorStatus.VALID);
        monitor.setAccessRight(Monitor.Right.TEACHER);
        monitor.setSex(Monitor.MonitorSex.MAN);
        monitor.setCreateTime(new Date());
        //monitor.setUpdateTime(new Date());
        monitorService.saveMonitor(monitor);
    }

    @Test
    public void testGetMonitoredByAccesstoken(){
        Monitor monitor = monitorService.getMonitorByAccesstoken("C418DF31F33C90CA582BFCC4F85A38ED");
    }

    @Test
    public void testGetAllMonitoredBymonitorByPage(){
        Monitor monitor = new Monitor();
        monitor.setMonitorNo(1);
        List<Monitored> monitoreds = monitorService.getAllMonitoredByMonitorByPage(1,new Page(1,1));
        System.out.println(monitoreds.size());
        System.out.println(monitoreds.get(0).getStudentNo());
    }

    */
/*@Test
    public void testGetAllMonitoredBymonitor(){
        Monitor monitor = new Monitor();
        monitor.setMonitorNo(1);
        List<Monitored> monitoreds = monitorService.getAllMonitoredByMonitor(1);
        System.out.println(monitoreds.size());
        System.out.println(monitoreds.get(0).getStudentNo());
    }*//*

}
*/
