package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.dao.PositionDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/6 22:55
 * @Description:
 */
@Repository(value = "positionDao")
public class PositionDaoImpl extends HibernateGenericDao<Position,Integer> implements PositionDao{

    @Override
    public Position getNewestPosition(String monitoredNo) {
        String hql = "from Position p where p.userId=? and p.createTime in (select max(a.createTime) from Position a)";
        Query query = getSession().createQuery(hql).setString(0,monitoredNo);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Position position = (Position) query.uniqueResult();

        /*uniqueResult()返回对象为object*/

        /*String hql = "select new cn.edu.Position(positionNo,monitoredNo,addrLong,addrLat," +
                "address,max(createTime))from Position where monitoredNo=:monitoredNo";
        Query query = getSession().createQuery(hql).setString("monitoredNo",monitoredNo);
        Position position = (Position) query.uniqueResult();*/
        return position;
    }

    /**
     * @param monitorNo
     * @return
     */
    @Override
    public List<Position> getAllNewestPosition(String monitorNo) {
        /*String hql = "select new cn.edu.Position(positionNo,monitoredNo,addrLong,addrLat,address,max(createTime))" +
                " from Position where monitoredNo in " +
                "(select monitoredNo from MonitoredAndMonitor where monitorNo=:monitorNo) group by monitoredNo order by createTime desc ";*/
        /*String sql = "SELECT * FROM " +
                "(SELECT * FROM t_position WHERE MONITORED_NO in " +
                "(SELECT monitored_no from t_monitored_and_monitor where MONITOR_NO=?)" +
                " ORDER BY CREATE_TIME DESC)" +
                " as p GROUP BY p.monitored_no;";*/
        String hql = "from Position p where p.userId in (select m.monitorUserId from MonitoredAndMonitor m where m.monitoredNo = ?) and p.createTime in (select max(a.createTime) from Position a group by a.userId)";
        Query query = getSession().createQuery(hql).setString(0,monitorNo);
        //Query query = getSession().createSQLQuery(sql).addEntity(Position.class).setString(0,monitorNo);
        List<Position> positions = query.list();
        return positions;
    }
    @Override
    public List<Position> getActivitiesObjectNewestPosition(String monitorNo,Integer actNo)
    {
        System.out.println(monitorNo+"  "+actNo);
        /*String sql = "SELECT * FROM " +
                "(SELECT * FROM t_position WHERE MONITORED_NO in " +
                "(SELECT monitored_no from t_monitored_and_monitor where (MONITOR_NO=? AND (relation_Ship='所有人' OR relation_Ship=?)))" +
                " ORDER BY CREATE_TIME DESC)" +
                " as p GROUP BY p.monitored_no;";*/
        String hql = "from Position p where p.userId in (select u.monitoredNo from UserAndActivities u where u.creatorNo = ? and u.actNo = ?) and p.createTime in (select max(a.createTime) from Position a group by a.userId)";
        Query query = getSession().createQuery(hql).setString(0,monitorNo).setInteger(1,actNo);
        return query.list();
    }

    @Override
    public List<Position> getOutActPosition(String monitoredNo,String actNo) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.println(time);
        String hql = "from Position p where p.userId = ? and p.createTime >=(select a.aplyUpplmt from Activities a where a.actNo=?) and p.createTime <= ? order by p.positionNo";
        Query query = getSession().createQuery(hql).setString(0,monitoredNo).setString(1,actNo).setTimestamp(2,time);
        return query.list();
    }

    @Override
    public List<Position> getRangeSharePositon(String monitoredNo, Timestamp StartDate, Timestamp StopDate) {
        String hql = "from Position p where p.userId = ? and p.createTime>=? and p.createTime<=? order by p.positionNo";
        Query query = getSession().createQuery(hql).setString(0,monitoredNo).setTimestamp(1,StartDate).setTimestamp(2,StopDate);
        return query.list();
    }
}
