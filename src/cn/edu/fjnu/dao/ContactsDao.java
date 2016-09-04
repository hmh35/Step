package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:56
 * @Description:
 */
public interface ContactsDao extends GenericDao<Contacts,Integer>{

    /**
     * 通过被监护人No删除
     * @param monitoredNo
     */
    void deleteByMonitored(String monitoredNo);

}
