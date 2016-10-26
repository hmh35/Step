package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.PositionDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 22:56
 * @Description:
 */
@Service
public class PositionServiceImpl implements PositionService{

    private Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Resource
    private PositionDao positionDao;

    @Override
    public void savePosition(Position position) {
        if(position == null || position.getUserId() == null || position.getUserId() == ""){
            logger.info("savePosition | this position is null");
            throw new AppRTException(AppExCode.P_MONITORED_NOT_EXISTS,"参数丢失，无法保存被监护人地理位置");
        }
        positionDao.save(position);
        System.out.println(111);
    }

    /*
    * 获取最新位置
    * */
    @Override
    public Position getNewestPosition(String monitoredNo) {
        return positionDao.getNewestPosition(monitoredNo);
    }


    /*
    * 获取监护人手下的所有被监护人最新地理位置
    * */
    @Override
    public List<Position> getNewestAll(String monitorNo) {
        return positionDao.getAllNewestPosition(monitorNo);
    }

    @Override
    public  List<Position> getActivitiesObjectNewestPosition(String monitorNo,Integer actNo){
        if(monitorNo == null || monitorNo == ""){
            logger.info("monitorNo||monitorNo is null");
            throw  new AppRTException(AppExCode.P_MONITORED_NOT_EXISTS,"对象不存在，无法获取位置信息");
        }
        if(actNo == null){
            logger.info("actNo || actNo is null");
            throw  new AppRTException(AppExCode.AC_NOT_FOUND,"不存在该监护人的活动");
        }
        List<Position> positionList;
        positionList=positionDao.getActivitiesObjectNewestPosition(monitorNo,actNo);
        System.out.println(positionList);
        return positionList;
    }

    @Override
    public List<Position> getAllPosition(String monitoredNo) {
        if(monitoredNo == null || monitoredNo == ""){
            logger.info("getAllPosition | monitoredNo is null");
            throw new AppRTException(AppExCode.NOT_EXIST_POSITION,"不存在历史轨迹");
        }
        List<Position> positionList = positionDao.findAllByOneProperty("userId",monitoredNo);
        if(positionList.size() <= 0){
            logger.info("getAllPosition | not exits position");
            throw new AppRTException(AppExCode.NOT_EXIST_POSITION,"不存在历史轨迹");
        }
        return positionList;
    }

    @Override
    public List<Position> getPositionRange(String monitoredNo,Date time) {
        if(monitoredNo == null || monitoredNo ==""){
            logger.info("monitoredNo | monitoredNo is null");
            throw new AppRTException(AppExCode.U_IS_EXISTS,"该用户不存在");
        }
        List<Position> positions = positionDao.getPositionRange(monitoredNo,time);
        return positions;
    }
}
