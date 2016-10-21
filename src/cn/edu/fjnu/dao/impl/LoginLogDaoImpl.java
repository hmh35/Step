package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.LoginLog;
import cn.edu.fjnu.dao.LoginLogDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


@Repository(value = "loginLogDao")
public class LoginLogDaoImpl extends HibernateGenericDao<LoginLog,Integer> implements LoginLogDao{
    @Override
    public LoginLog getLoginLog(String accesstoken) {
        String hql = "from LoginLog lo where lo.accesstoken = ?";
        Query query = getSession().createQuery(hql).setString(0,accesstoken);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (LoginLog) query.uniqueResult();
    }
}
