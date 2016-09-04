package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Config;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 23:46
 * @Description: 客户端系统配置service
 */
public interface ConfigService {

    /**
     * 获取用户客户端系统配置
     *
     * @param userName
     * @return
     */
    Config getConfig(String userName);

    /**
     * 创建用户客户端系统配置
     *
     * @param userName
     * @return
     */
    Config createConfig(String userName);

    /**
     * 更新用户客户端系统配置
     *
     * @param config
     * @return
     */
    Config updateConfig(Config config);


}
