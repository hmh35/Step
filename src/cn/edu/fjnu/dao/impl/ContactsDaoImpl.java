package cn.edu.fjnu.dao.impl;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ContactsDao;
import cn.edu.fjnu.dao.base.HibernateGenericDao;
import cn.edu.fjnu.exception.AppRTException;
import org.springframework.stereotype.Repository;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:57
 * @Description:
 */
@Repository(value = "contactsDao")
public class ContactsDaoImpl extends HibernateGenericDao<Contacts,Integer> implements ContactsDao {

    @Override
    public void deleteByMonitored(String monitoredNo) {
        Contacts contacts = super.uniqueResult("monitoredNo",monitoredNo);
        if(contacts == null){
            throw new AppRTException(AppExCode.CON_CONTACTS_NOT_EXISTS,"不存在该用户的对应的联系人");
        }
        delete(contacts);
    }
}
