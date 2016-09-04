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
        if(position == null || position.getMonitoredNo() == null || position.getMonitoredNo() == ""){
            logger.info("savePosition | this position is null");
            throw new AppRTException(AppExCode.P_MONITORED_NOT_EXISTS,"参数丢失，无法保存被监护人地理位置");
        }
        positionDao.save(position);
    }

    @Override
    public Position getNewestPosition(String monitoredNo) {
        return positionDao.getNewestPosition(monitoredNo);
    }

    @Override
    public List<Position> getNewestAll(String monitorNo) {
        return positionDao.getAllNewestPosition(monitorNo);
    }

    @Override
    public List<Position> getAllPosition(String monitoredNo) {
        if(monitoredNo == null || monitoredNo == ""){
            logger.info("getAllPosition | monitoredNo is null");
            throw new AppRTException(AppExCode.NOT_EXIST_POSITION,"不存在历史轨迹");
        }
        List<Position> positionList = positionDao.findAllByOneProperty("monitoredNo",monitoredNo);
        if(positionList.size() <= 0){
            logger.info("getAllPosition | not exits position");
            throw new AppRTException(AppExCode.NOT_EXIST_POSITION,"不存在历史轨迹");
        }
        return positionList;
    }
}
