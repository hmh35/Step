/*
package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.dao.MonitoredDao;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.utils.Md5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

*/
/**
 * @Author: linqiu
 * @Date: 2016/3/4 21:25
 * @Description:
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MonitoredServiceTest {

    private Logger logger = LoggerFactory.getLogger(MonitoredServiceTest.class);

    @Resource
    private MonitoredService monitoredService;

    @Resource
    private MonitoredDao monitoredDao;

    @Test
    public void testSave(){
        Monitored monitored = new Monitored();
        monitored.setStudentNo("123012012132");
        monitored.setPassword(Md5.digest("123456".getBytes()));
        monitored.setAge(25);
        monitored.setRealName("林秋");
        monitored.setIdentityCardNo("35012119920305****");
        monitored.setAdmissionTime(new Date());
        monitored.setLengthOfSchooling(4);
        monitored.setSex(Monitored.MonitoredSex.MAN);
        monitored.setStatus(Monitored.MonitoredStatus.VALID);
        monitored.setCreateTime(new Date());
        monitored.setPhoneNumber("15659928899");
        //monitored.setUpdateTime(new Date());
        monitoredService.saveMonitored(monitored);
    }

    @Test
    public void testLogin(){
        Monitored monitored = monitoredService.login("123012012131",Md5.digest("123456".getBytes()));
        System.out.println(monitored.getStudentNo());
    }

    @Test
    public void testUpdateMonitored(){
        Monitored monitored = new Monitored();
        monitored.setStudentNo("123012012131");
        monitored.setPassword(Md5.digest("654321".getBytes()));
        monitored.setAge(25);
        monitored.setRealName("唐文明1");
        monitored.setIdentityCardNo("45022219920305****");
        monitored.setAdmissionTime(new Date());
        monitored.setLengthOfSchooling(4);
        monitored.setSex(Monitored.MonitoredSex.MAN);
        monitored.setStatus(Monitored.MonitoredStatus.INVALID);
        monitored.setCreateTime(new Date());
        monitored.setPhoneNumber("15659929999");
        monitored.setUpdateTime(new Date());
        monitoredService.updateMonitor(monitored);
    }

    @Test
    public void testUpdatePassword(){
        monitoredService.updatePassword("123012012131",Md5.digest("123456".getBytes()),Md5.digest("654321".getBytes()));
    }

    @Test
    public void testForbiddenMonitored(){
        monitoredService.forbiddenMonitored("123012012131");
    }

    @Test
    public void testUnForbiddenMonitored(){
        monitoredService.unforbiddenMonitored("123012012131");
    }

    @Test
    public void testBase64(){
        String password = "123012012130";
        //加密
        String ret = new BASE64Encoder().encode(password.getBytes());
        System.out.println("\n========================"+"加密后"+ret.toUpperCase());
        //解密
        String ret1 = null;
        try {
             ret1 = new String(new BASE64Decoder().decodeBuffer(ret));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n========================"+"解密后"+ret1.toUpperCase());
    }

    @Test
    public void getMonitoredByAccesstoken(){
        Monitored monitored = monitoredService.getMonitoredByAccesstoken("836C7602D8F9193E3BB61E5459F7147E");
        if(monitored.getChannelId().equals("")){
            System.out.println("is null");
        }
    }
}
*/
