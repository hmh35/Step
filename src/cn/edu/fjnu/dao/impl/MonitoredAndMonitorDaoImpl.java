package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.dao.MonitoredAndMonitorDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/22 21:09
 * @Description:
 */
@Repository(value = "monitoredAndMonitorDao")
public class MonitoredAndMonitorDaoImpl extends HibernateGenericDao<MonitoredAndMonitor,Integer> implements MonitoredAndMonitorDao{

}
