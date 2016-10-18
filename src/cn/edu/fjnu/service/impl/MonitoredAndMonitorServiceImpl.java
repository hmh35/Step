package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.MonitoredAndMonitorDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.MonitoredAndMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/22 21:10
 * @Description:
 */
@Service
public class MonitoredAndMonitorServiceImpl implements MonitoredAndMonitorService{

   /* private Logger logger = LoggerFactory.getLogger(MonitoredAndMonitorServiceImpl.class);

    @Resource
    private MonitoredAndMonitorDao monitoredAndMonitorDao;

    @Override
    public void createMonitoredAndMonitor(Integer monitoredNo, Integer monitorNo) {
        if(monitoredNo == null || monitorNo == null){
            logger.info("createMonitoredAndMonitor | monitoredNo or monitorNo is null");
            throw new AppRTException(AppExCode.MONITORED_AND_MONITOR_ERROR,"无法创建被监护人和监护人联系");
        }
        MonitoredAndMonitor monitoredAndMonitor = new MonitoredAndMonitor();
        monitoredAndMonitor.setMonitoredNo(monitoredNo.toString());
        monitoredAndMonitor.setMonitorNo(monitorNo.toString());
        monitoredAndMonitor.setCreateTime(new Date());
        monitoredAndMonitor.setUpdateTime(new Date());
        monitoredAndMonitorDao.save(monitoredAndMonitor);

    }*/
}
