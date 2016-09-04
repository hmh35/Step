package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 9:47
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ConfigServiceTest {

    private Logger logger = LoggerFactory.getLogger(ConfigServiceTest.class);

    @Resource
    private ConfigService configService;

    @Test
    public void testGetConfig() {
        Config config = configService.getConfig("123012012130");
        System.out.println(config.getIsMonitored());
    }

    @Test
    public void testCreateConfig() {
        //configService.createConfig("123012012130");
        configService.createConfig("duchengyu");
    }

    /*@Test
    public void testUpdateConfig() {
        Config config = new Config();
        config.setUserName("123012012130");
        //config.setConfNo(444);
        config.setIsMonitored(Config.IsMonitored.VALID);
        configService.updateConfig(config);
    }*/

}
