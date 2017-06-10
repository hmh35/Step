package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.dao.SynamicDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.dao.base.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by HMH on 2016/11/25.
 */
@Repository(value = "SynamicDao")
public class SynamicDaoImpl extends HibernateGenericDao<Synamic,Integer> implements SynamicDao {

    @Override
    public List getRunningRingByCreator(String CreatorNo,Page page){
        System.out.println(CreatorNo);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //String hql = "from Synamic a WHERE a.aplyUpplmt<? AND a.creatorNo IN (SELECT u.creatorNo FROM UserAndRing u WHERE u.creatorNo=?) AND a.ringNo IN (SELECT u.ringNo FROM UserAndRing u where u.creatorNo = ?)";
        String hql = "from Synamic a where a.creatorNo = ?";
        Query query = getSession().createQuery(hql).setTimestamp(0, time).setString(1, CreatorNo)/*.setString(2,CreatorNo)*/;
        System.out.println(query);
        query.setFirstResult((page.getStartIndex()-1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        List SynamicList=query.list();
        System.out.println();
        return SynamicList;
    }

    @Override
    public List getSynamicByCreator(String creatorNo) {
        String hql = "from Synamic a where a.creatorNo = ?";
        Query query = getSession().createQuery(hql).setString(0,creatorNo);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List SynamicList=query.list();
        return SynamicList;
    }

    @Override
    public List getAllSynamic() {
        String hql = "from Synamic a where a.creatorNo !=null ";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List SynamicList=query.list();
        return SynamicList;
    }

    @Override
    public Synamic getRunningRingByCCT(String creatorNo, String createTime){
        System.out.println(createTime);
        String hql = "from Synamic a where a.creatorNo = ? and a.createTime =  ?";
        Query query = getSession().createQuery(hql).setString(0,creatorNo).setString(1,createTime);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Synamic synamic = (Synamic) query.uniqueResult();
       // System.out.println(synamic.getRingId());
        return synamic;
    }

    @Override
    public Synamic getActNoRunningRing(Integer ringId){
        String hql = "from Synamic a where a.ringId = ?";
        Query query = getSession().createQuery(hql).setInteger(0,ringId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (Synamic) query.uniqueResult();
    }

    @Override
    public void updateComment(Integer reviewId,Integer ringId) {
        String hql ="update Synamic a set a.reviewId=? where a.ringId=?";
        Query query =getSession().createQuery(hql).setInteger(0,reviewId).setInteger(1,ringId);
        query.executeUpdate();
    }

    @Override
    public void updateThumb(Integer thumb,Integer ringId) {
        String hql ="update Synamic a set a.thumb=? where a.ringId=?";
        Query query =getSession().createQuery(hql).setInteger(0,thumb).setInteger(1,ringId);
        query.executeUpdate();
    }

    @Override
    public void updateHeadPicture(String path, String creatorNo) {
        String hql ="update Synamic a set a.userHeadPicture=? where a.creatorNo=?";
        Query query =getSession().createQuery(hql).setString(0,path).setString(1,creatorNo);
        query.executeUpdate();
    }

    @Override
    public void updateSynamicCreatorName(String userName, Integer ringId) {
        String hql ="update Synamic a set a.creatorName=? where a.ringId=?";
        Query query =getSession().createQuery(hql).setString(0,userName).setInteger(1,ringId);
        query.executeUpdate();
    }


}
