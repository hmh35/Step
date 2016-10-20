/*
package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

*/
/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: Tester类
 *//*


*/
/** 用于Spring bean注解注入使用 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Tester {

    //private ApplicationContext context = null;

    private Logger logger = LoggerFactory.getLogger(Tester.class);

    @Resource(name = "userDao")
    private UserDao userDao;

    @Autowired
    private UserService userService;


    @Test
    public void testDataSource() throws SQLException {
        //context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //userDao = context.getBean(UserDao.class);

        User user = new User("heiheiaaaaaaaaaaaaa1111",2);
        System.out.println(userDao.toString());
        logger.info(userDao.toString());
        userDao.save(user);
    }

    @Test
    public void testUserService() {
        //User user = new User("dadasd",2,new Date());
        //System.out.println(userDao.toString());
        //logger.info(userDao.toString());
        //userService.saveUser(user);

    }

    @Test
    public void testUserDao(){
        User userTemp = userDao.uniqueResult("names","name222");
        User user = new User("name333");
        user.setNo(userTemp.getNo());
        userDao.update(user);
    }

    @Test
    public void testFastJson(){
        User user = new User("你好");
        System.out.println(JSON.toJSONString(user));
    }


    @Test
    public void testFastJson1(){
        //User user = userDao.getMaxAge();
        //System.out.println(user.getAge());
        */
/*boolean b = true;
        String isOntime = JSONObject.toJSONString(b);
        System.out.println(isOntime);
        boolean a = JSONObject.parseObject(isOntime,boolean.class);
        if(a){
            System.out.println("===============");
        }*//*

        String n = "123012012130";
        System.out.println(n.substring(n.length()-5,n.length()-1));
    }

    @Test
    public void testBaiduPush(){
        //Activities activities = new Activities();
        //activities.setAddress("福建师范大学");
       // BaiduPush.pushActivity();
        String ss = "nihao;nihao1";
        String[] s = ss.split(";");
        for(int i = 0;i<s.length;i++){
            System.out.println("========="+s[i]);
        }
        System.out.println(s[1]);
    }

}
*/
