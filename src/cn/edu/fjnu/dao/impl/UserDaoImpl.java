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
    public void UpdateChannelId(String channelId, Integer userId) {
        String hql="update User m set m.channelId = ? where userId = ?";
        Query query = getSession().createQuery(hql).setString(0,channelId).setInteger(1, userId);
        query.executeUpdate();
    }

    @Override
    public List getAllMonitoredByMonitor(Integer monitorNo, String pushObject) {
        System.out.println("5555:"+pushObject);
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=?)";
        //String hql = "from user u,MonitoredAndMonitor m where u.userId = m.monitoredNo and monitorNo = ?";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo);
        return query.list();
    }

    @Override
    public List getProperMonitoredByMonitor(Integer monitorNo, String pushObject) {
        String hql = "from User u where u.userId in (SELECT monitoredNo from  MonitoredAndMonitor where monitorNo=? and relationShip=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitorNo).setString(1,pushObject);
        return query.list();
    }

    @Override
    public List getMonitorByMonitoredNo(Integer monitoredNo) {
        String hql = "from Monitor m where m.monitorNo in (SELECT monitorNo from  MonitoredAndMonitor where monitoredNo=?)";
        Query query = getSession().createQuery(hql).setInteger(0,monitoredNo);
        return query.list();
    }
    /*@Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        //获取和当前线程绑定的session
        return sessionFactory.getCurrentSession();
    }*/

  /* @Override
    public void saveUser(User user) {
        *//*Session session = sessionFactory.openSession();
        session.save(user);*//*
        save(user);
        //throw new RuntimeException();
    }

    @Override
    public User getUserById(Integer id) {
        return (User) getSession().get(User.class,id);
    }*/
}
