package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.LoginLog;
import cn.edu.fjnu.dao.base.GenericDao;


public interface LoginLogDao extends GenericDao<LoginLog,Integer>{
    LoginLog getLoginLog(String accesstoken);
}
