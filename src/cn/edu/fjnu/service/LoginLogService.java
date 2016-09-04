package cn.edu.fjnu.service;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 20:15
 * @Description: 登录日志service
 */
public interface LoginLogService {

    /**
     * 创建accesstoken
     * @param userName 用户名
     * @return accesstoken
     */
    String createAccesstoken(String userName);

    /**
     * * 更新accessToken
     * @param oldAccesstoken 旧Accesstoken
     * @return 新accesstoken
     */
    String updateAccesstoken(String oldAccesstoken);

    /**
     * 获取用户号
     * @param accesstoken
     * @return 用户号
     */
    String getUserName(String accesstoken);

    /**
     * 验证accesstoken是否过期
     * @param accesstoken
     * @return 用户名
     */
    String checkAccesstoken(String accesstoken);

    /**
     * 通过userName更新accesstoken
     * @param userName
     * @return
     */
    String updateAccesstokenByUserNo(String userName);

    /**
     * 通过userName获取accesstoken
     * @param userName
     * @return
     */
    String getAccesstoken(String userName);
}
