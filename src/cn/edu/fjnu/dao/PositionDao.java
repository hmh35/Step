package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.dao.base.GenericDao;
import javafx.geometry.Pos;

import java.sql.Timestamp;
import java.util.Date;
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

    List<Position> getActivitiesObjectNewestPosition(String monitorNo,Integer actNo);

    /*
    * 获取对象固定时间轨迹
    * */
    List<Position> getOutActPosition(String monitoredNo,String actNo);

    /*
    * 位置分享
    * */
    List<Position> getRangeSharePositon(String monitoredNo, Timestamp StartDate, Timestamp StopDate);
}
