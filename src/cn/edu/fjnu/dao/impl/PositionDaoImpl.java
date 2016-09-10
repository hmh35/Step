package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.dao.PositionDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
        String hql = "from Position p where p.monitoredNo=:monitoredNo order by p.positionNo desc";

        Query query = getSession().createQuery(hql).setString("monitoredNo",monitoredNo);
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
     * 该查询用的是sql语句查询，hql暂时没有找到好的查询办法
     * @param monitorNo
     * @return
     */
    @Override
    public List<Position> getAllNewestPosition(String monitorNo) {
        /*String hql = "select new cn.edu.Position(positionNo,monitoredNo,addrLong,addrLat,address,max(createTime))" +
                " from Position where monitoredNo in " +
                "(select monitoredNo from MonitoredAndMonitor where monitorNo=:monitorNo) group by monitoredNo order by createTime desc ";*/
        String sql = "SELECT * FROM " +
                "(SELECT * FROM t_position WHERE MONITORED_NO in " +
                "(SELECT monitored_no from t_monitored_and_monitor where MONITOR_NO=?)" +
                " ORDER BY CREATE_TIME DESC)" +
                " as p GROUP BY p.monitored_no;";

        //Query query = getSession().createQuery(hql).setString("monitorNo",monitorNo);
        Query query = getSession().createSQLQuery(sql).addEntity(Position.class).setString(0,monitorNo);
        List<Position> positions = query.list();
        return positions;
    }
    @Override
    public List<Position> getActivitiesObjectNewestPosition(String monitorNo,String pushObject)
    {
        System.out.println("推送对象："+pushObject);
        String sql = "SELECT * FROM " +
                "(SELECT * FROM t_position WHERE MONITORED_NO in " +
                "(SELECT monitored_no from t_monitored_and_monitor where (MONITOR_NO=? AND (relation_Ship='所有人' OR relation_Ship=?)))" +
                " ORDER BY CREATE_TIME DESC)" +
                " as p GROUP BY p.monitored_no;";
        Query query = getSession().createSQLQuery(sql).addEntity(Position.class).setString(0,monitorNo).setString(1,pushObject);
        List<Position> positions = query.list();
        return positions;
    }
}
