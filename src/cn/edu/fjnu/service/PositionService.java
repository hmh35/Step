package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Position;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 22:56
 * @Description:
 */
public interface PositionService {

    /**
     * 保存被监护人位置
     *
     * @param position
     */
    void savePosition(Position position);

    /**
     * 获取被监护人地理位置
     *
     * @param monitoredNo
     */
    Position getNewestPosition(String monitoredNo);

    /**
     * 获取监护人手下的所有被监护人的最新地理位置
     *
     * @param monitorNo
     * @return
     */
    List<Position> getNewestAll(String monitorNo);


    /**
     * 获取所有位置
     * @param monitoredNo
     * @return
     */
    List<Position> getAllPosition(String monitoredNo);
}
