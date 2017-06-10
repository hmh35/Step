package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Thumb;
import cn.edu.fjnu.dao.ThumbDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * Created by HMH on 2016/12/3.
 */
@Repository(value = "ThumbDao")
public class ThumbDaoImpl extends HibernateGenericDao<Thumb,Integer>implements ThumbDao{
    @Override
    public Thumb getMyThumbByRingId(String ringId) {
        String hql = "from Thumb a where a.synamicId = ?";
        Query query = getSession().createQuery(hql).setString(0,ringId);
        query.setFirstResult(0);
        query.setMaxResults(1);
     //   Thumb thumb=(Thumb) query.uniqueResult();
        return (Thumb) query.uniqueResult();
    }

    @Override
    public List<Thumb> getMyThumb(String ringId) {
        String hql = "from Thumb a where a.synamicId = ?";
        Query query = getSession().createQuery(hql).setString(0,ringId);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List thumbList=query.list();
        return thumbList;
    }


    @Override
    public List countThumb(String ringId) {
        String hql ="from Thumb a where a.synamicId=?";
        Query query =getSession().createQuery(hql).setString(0,ringId);
        query.setFirstResult(0);
        query.setMaxResults(20);
        List thumbtList =query.list();
        return thumbtList;
    }


    @Override
    public int judge(Integer ringId, String thumberId) {
        String hql ="from Thumb a where a.synamicId = ? and a.thumberId = ?";
        Query query=this.getSession().createQuery(hql).setInteger(0, ringId).setString(1,thumberId);
        query.setFirstResult(0);
        query.setMaxResults(5);
        List judgeList =query.list();
        return  judgeList.size();
    }
}
