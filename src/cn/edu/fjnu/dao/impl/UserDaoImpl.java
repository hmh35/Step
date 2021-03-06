package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Dao实现层
 */
@Repository(value = "userDao")
public class UserDaoImpl extends HibernateGenericDao<User,Integer> implements UserDao{
    @Override
    public User getMaxAge() {
        //String hql = "from User where age=(select max(age) from User)";
        String hql = "select new cn.edu.User(no,names,max(age),createTime) from User";
        Query query = getSession().createQuery(hql);
        User user = (User) query.list().get(0);
        return user;
    }

    @Override
    public List getAllMonitoredByMonitor(Integer monitorNo, String pushObject) {
        System.out.println("5555:"+pushObject);
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo);
        return query.list();
    }

    @Override
    public List getProperMonitoredByMonitor(Integer monitorNo,String pushObject) {
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=? and relationShip=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo).setString(1,pushObject);
        return query.list();
    }

    @Override
    public List getMonitorByMonitoredNo(Integer monitoredNo) {
        String hql = "from User u where u.userId in (SELECT monitorNo from  MonitoredAndMonitor where monitoredNo=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitoredNo);
        return query.list();
    }

    @Override
    public User getUser(String userId) {
        String hql = "from User u where u.userId = ?";
        Query query = getSession().createQuery(hql).setString(0,userId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (User)query.uniqueResult();
    }

    @Override
    public void updateHeadPicture( String path,Integer userId) {
        String hql ="update User a set a.userHeadPicture=? where a.userId =?";
        Query query =getSession().createQuery(hql).setString(0,path).setInteger(1,userId);
        query.executeUpdate();
    }


    @Override
    public void UpdateChannelId(String channelId, Integer userId) {
        String hql="update User m set m.channelId = ? where m.userId = ?";
        Query query = getSession().createQuery(hql).setString(0,channelId).setInteger(1, userId);
        query.executeUpdate();
    }

    @Override
    public void updatePwd(String newPwd, Integer userId) {
        String hql ="update User m set m.userPwd=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setString(0,newPwd).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateWeight(String weight, Integer userId) {
        String hql ="update User m set m.userWeight=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setString(0,weight).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateHeight(String Height, Integer userId) {
        String hql ="update User m set m.userHeight=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setString(0,Height).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateuserName(String userName, Integer userId) {
        String hql ="update User m set m.userName=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setString(0,userName).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateuserSex(String userSex, Integer userId) {
        String hql ="update User m set m.userSex=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setString(0,userSex).setInteger(1,userId);
        query.executeUpdate();
    }

    @Override
    public void updateuserAge(Integer age, Integer userId) {
        String hql ="update User m set m.userAge=? where m.userId = ?";
        Query query =getSession().createQuery(hql).setInteger(0,age).setInteger(1,userId);
        query.executeUpdate();
    }
}
