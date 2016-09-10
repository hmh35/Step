package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 22:54
 * @Description:
 */
public interface PositionDao extends GenericDao<Position,Integer>{
    /**
     * 获取最新地址
     * @param monitoredNo
     * @return
     */
    Position getNewestPosition(String monitoredNo);

    List<Position> getAllNewestPosition(String monitorNo);

    List<Position> getActivitiesObjectNewestPosition(String monitorNo,String pushObject);
}
