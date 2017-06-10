package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.UserAndRing;
import cn.edu.fjnu.dao.UserAndRingDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by HMH on 2016/11/26.
 */

@Repository(value = "UserAndRing")
public class UserAndRingDaoImpl extends HibernateGenericDao<UserAndRing,Integer> implements UserAndRingDao{
    @Override
    public void deleteByRingNo(Integer actNo) {
        String hql = "delete UserAndActivities u where u.actNo = ?";
        Query query = getSession().createQuery(hql).setInteger(0,actNo);
        query.executeUpdate();
    }
}
