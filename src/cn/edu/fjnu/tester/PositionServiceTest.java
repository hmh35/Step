package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.dao.PositionDao;
import cn.edu.fjnu.service.PositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 23:21
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PositionServiceTest {
    private Logger logger = LoggerFactory.getLogger(PositionServiceTest.class);

    @Resource
    private PositionService positionService;

    @Resource
    private PositionDao positionDao;

    @Test
    public void testSave(){
        Position position = new Position();
        //position.setPositionNo(1);
        position.setMonitoredNo("123121301");
        position.setAddrLat(119.397354);
        position.setAddrLong(119.397354);
        position.setAddress("闽侯青口1111111111111111111");
        position.setCreateTime(new Date());
        positionService.savePosition(position);
    }

    @Test
    public void testgetNewestPositionDao(){
        //positionDao.getNewestPosition("12312130");
        Position position = positionDao.getNewestPosition("32");
        System.out.println(position.getCreateTime());
        System.out.println(position.getAddress());
    }

    @Test
    public void testgetNewestPositionService(){
        //positionDao.getNewestPosition("12312130");
        Position position = positionService.getNewestPosition("29");
        System.out.println(position.getCreateTime());
        System.out.println(position.getAddress());
    }

    @Test
    public void testGetNewestAll(){
        List<Position> positions = positionDao.getAllNewestPosition("1");
        System.out.println(positions.size());
    }

    @Test
    public void testGetNewestAllService(){
        List<Position> positions = positionService.getNewestAll("1");
        System.out.println(positions.size());
        System.out.println(positions.get(0).getMonitoredNo());
    }


}
