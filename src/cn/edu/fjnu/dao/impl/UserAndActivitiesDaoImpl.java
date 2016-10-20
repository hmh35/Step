package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Activities;
import cn.edu.fjnu.beans.UserAndActivities;
import cn.edu.fjnu.dao.UserAndActivitiesDao;
import cn.edu.fjnu.dao.base.GenericDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/10/16.
 */
@Repository(value = "UserAndActivities")
public class UserAndActivitiesDaoImpl extends HibernateGenericDao<UserAndActivities,Integer> implements UserAndActivitiesDao {
    @Override
    public void deleteByActNo(Integer actNo) {
        String hql = "delete UserAndActivities u where u.actNo = ?";
        Query query = getSession().createQuery(hql).setInteger(0,actNo);
        query.executeUpdate();
    }
}
