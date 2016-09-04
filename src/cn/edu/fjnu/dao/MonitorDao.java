package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.dao.base.GenericDao;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 10:41
 * @Description: 监护人dao
 */
public interface MonitorDao extends GenericDao<Monitor,Integer>{

    /**
     * 分页获取监护人手下的所有被监护人
     * @param monitorNo
     * @return
     */
    List getAllMonitoredByMonitorByPage(Integer monitorNo, Page page);
    /**
     * 获取监护人手下的所有被监护人
     * @param monitorNo
     * @return
     */
    List getAllMonitoredByMonitor(Integer monitorNo,String pushObject);

    /**
     * 得到指定的被推送对象
     *
     * */
    List getProperMonitoredByMonitor(Integer monitorNo,String pushObject);
}
