package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Config;
import cn.edu.fjnu.dao.ConfigDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.springframework.stereotype.Repository;


@Repository(value = "configDao")
public class ConfigDaoImpl extends HibernateGenericDao<Config,Integer> implements ConfigDao {
}
