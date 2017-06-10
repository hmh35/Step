package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.dao.LinkDataDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HMH on 2016/12/10.
 */
@Repository(value = "LinkDataDao")
public class LinkDataDaoImpl extends HibernateGenericDao<NewsEntity,Integer> implements LinkDataDao{
    @Override
    public List getData() {
        String hql = "from NewsEntity";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List linkData=query.list();
        return linkData;
    }
}
