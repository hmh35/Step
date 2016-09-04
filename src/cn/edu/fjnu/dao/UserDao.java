package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Dao接口层
 */
public interface UserDao extends GenericDao<User,Integer>{

    User getMaxAge();
}
