package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.LoginLog;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 20:14
 * @Description: 登录日志dao
 */
public interface LoginLogDao extends GenericDao<LoginLog,Integer>{
    LoginLog getLoginLog(String accesstoken);
}
