package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Comment;
import cn.edu.fjnu.dao.CommentDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HMH on 2016/12/5.
 */
@Repository(value = "CommentDao")
public class CommentDaoImpl extends HibernateGenericDao<Comment,Integer> implements CommentDao{
    @Override
    public Comment getMyCommentByRingId(String ringId) {
        String hql = "from Comment a where a.synamicId = ?";
        Query query = getSession().createQuery(hql).setString(0,ringId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Comment comment=(Comment) query.uniqueResult();
        return comment;
    }

    @Override
    public List CountComment(Integer synamicId) {
        String hql ="from Comment a where a.synamicId=?";
        Query query =getSession().createQuery(hql).setInteger(0,synamicId);
        query.setFirstResult(0);
        query.setMaxResults(20);
        List commentList =query.list();
        return commentList;

    }

    @Override
    public void setCmmentCount(String ringId, Integer count) {
        Comment comment =getMyCommentByRingId(ringId);
        comment.setCommentCount(count);

    }

    @Override
    public void updateHeadPicture(String path, Integer userId) {
        String hql ="update Comment a set a.observerHeadPicture=? where a.observerId =?";
        Query query =getSession().createQuery(hql).setString(0,path).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateuserName(String userName, Integer userId) {
        String hql ="update Comment m set m.observerName=? where m.observerId = ?";
        Query query =getSession().createQuery(hql).setString(0,userName).setInteger(1,userId);
        query.executeUpdate();
    }
}
