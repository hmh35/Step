package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ContactsDao;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 19:00
 * @Description:
 */
@Service
public class ContactsServiceImpl implements ContactsService{

    private Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);

    @Resource
    private ContactsDao contactsDao;

    @Override
    public void createContacts(Contacts contacts) {
        if(contacts == null
                || contacts.getContactName() == null || contacts.getContactName() == ""
                || contacts.getMonitoredNo() == null || contacts.getMonitoredNo() == ""
                || contacts.getContactPhone() == null || contacts.getContactPhone() == ""){
            logger.info("createContacts | this contacts is error");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"联系人信息错误，无法新增");
        }
        contactsDao.save(contacts);
    }

    @Override
    public List<Contacts> getAllContacts(String monitoredNo, Page page) {
        if(monitoredNo == null || monitoredNo == ""){
            logger.info("getAllContacts | this userName is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"用户名错误，无法进行查询");
        }
        List<Contacts> contactsList = contactsDao.findAllByProPage("monitoredNo",monitoredNo,page.getStartIndex(),page.getPageSize());
        if(contactsList.size() == 0){
            logger.info("getAllContacts | do not exists contacts");
            throw new AppRTException(AppExCode.CON_CONTACTS_NOT_EXISTS,"不存在该用户对应的联系人");
        }
        return contactsList;
    }

    @Override
    public void deleteContactsByMonitored(String monitoredNo,Contacts contacts) throws AppRTException{
        if(monitoredNo == null || monitoredNo == "" || contacts == null ){
            logger.info("deleteContactsByMonitored | this para is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"删除联系人参数错误，无法删除");
        }
        if(!monitoredNo.equals(contacts.getMonitoredNo())){
            logger.info("deleteContactsByMonitored | can not delete contact that not belong to you");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"无法删除不属于自己的联系人");
        }
        contactsDao.delete(contacts);
    }

    @Override
    public void updateContactByMonitored(Contacts contacts) {
        if(contacts == null || contacts.getContactNo() == null
                || contacts.getContactName() == null || contacts.getContactName() == ""
                || contacts.getMonitoredNo() == null || contacts.getMonitoredNo() == ""
                || contacts.getContactPhone() == null || contacts.getContactPhone() == ""){
            logger.info("createContacts | this contacts is error");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"联系人信息错误，无法新增");
        }
        contactsDao.update(contacts);
    }
}
