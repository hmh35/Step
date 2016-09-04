package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ConfigDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 23:46
 * @Description: 客户端系统配置service实现类
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Resource
    private ConfigDao configDao;

    @Override
    public Config getConfig(String userName) {
        if (userName == null || userName == "") {
            logger.info("getConfig | userName is null!");
            throw new AppRTException(AppExCode.C_COMMON_ERROR, "参数缺失，无法获取客户端系统配置");
        }
        Config config = configDao.uniqueResult("userName", userName);
        if(config == null){
            logger.info("getConfig | userNo is null!");
            throw new AppRTException(AppExCode.C_NOT_CONFIG, "不存在该用户的客户端系统配置");
        }
        //config.setUserName(null);
        config.setConfNo(null);
        return config;
    }

    @Override
    public Config createConfig(String userName) {
        if (userName == null || userName == "") {
            logger.info("createConfig | userName is null!");
            throw new AppRTException(AppExCode.C_COMMON_ERROR, "参数缺失，无法创建客户端系统配置");
        }
        Config config = new Config();
        config.setUserName(userName);
        config.setIsMonitored(Config.Status.VALID);
        config.setIsAlarm(Config.Status.VALID);
        config.setIsWarning(Config.Status.VALID);
        configDao.save(config);

        config.setUserName(null);
        config.setConfNo(null);
        return config;
    }

    @Override
    public Config updateConfig(Config config) {
        if (config == null) {
            logger.info("updateConfig | userNo is null!");
            throw new AppRTException(AppExCode.C_COMMON_ERROR, "参数缺失，无法创建客户端系统配置");
        }
        Config getconfig = configDao.uniqueResult("userName", config.getUserName());
        if(getconfig == null){
            logger.info("updateConfig | this config is not exists!");
            throw new AppRTException(AppExCode.C_NOT_CONFIG, "不存在该用户的客户端系统配置,更新失败");
        }
        config.setConfNo(getconfig.getConfNo());
        configDao.update(config);

        config.setUserName(null);
        config.setConfNo(null);
        return config;
    }
}
