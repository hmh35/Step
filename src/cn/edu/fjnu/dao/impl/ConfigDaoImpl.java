package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.dao.ConfigDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.springframework.stereotype.Repository;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 23:45
 * @Description:
 */
@Repository(value = "configDao")
public class ConfigDaoImpl extends HibernateGenericDao<Config,Integer> implements ConfigDao {
}
