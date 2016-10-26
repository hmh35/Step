package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.beans.MonitoredAndMonitor;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.ContactsDao;
import cn.edu.fjnu.dao.MonitoredAndMonitorDao;
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

    @Resource
    private MonitoredAndMonitorDao monitoredAndMonitorDao;

    @Override
    public void createContacts(MonitoredAndMonitor contacts) {
        if(contacts == null
                || contacts.getMonitoredNo() == null || contacts.getMonitorPhone() == ""
                || contacts.getMonitoredNo() == null || contacts.getMonitoredNo() == ""
                || contacts.getRelationShip() == null || contacts.getRelationShip() == ""){
            logger.info("createContacts | this contacts is error");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"联系人信息错误，无法新增");
        }
        if(!contactsDao.saveunique(contacts.getMonitoredNo(),contacts.getMonitorPhone())) {
            logger.info("createContacts | this contacts is existed");
            throw new AppRTException(AppExCode.CON_PARA_NULL, "联系人信息已存在，无法新增");
        }

        contactsDao.save(contacts);
    }

    @Override
    public List<MonitoredAndMonitor> getAllContacts(String monitoredNo, Page page) {
        if(monitoredNo == null || monitoredNo == ""){
            logger.info("getAllContacts | this userName is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"用户名错误，无法进行查询");
        }
        List<MonitoredAndMonitor> contactsList = contactsDao.findAllByProPage("monitoredNo",monitoredNo,page.getStartIndex(),page.getPageSize());
        if(contactsList.size() == 0){
            logger.info("getAllContacts | do not exists contacts");
            throw new AppRTException(AppExCode.CON_CONTACTS_NOT_EXISTS,"不存在该用户对应的联系人");
        }
        return contactsList;
    }

    @Override
    public void deleteContactsByMonitoredAndMonitor(Integer monitoredUserId,MonitoredAndMonitor deleteContacts) throws AppRTException{
        if(monitoredUserId == null ||  deleteContacts.getMonitoredNo() == null ||deleteContacts.getMonitoredNo().equals(monitoredUserId)){
            logger.info("deleteContactsByMonitored | this para is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"删除联系人参数错误，无法删除");
        }
        /*if(!monitoredNo.equals(monitoredAndMonitor.getMonitoredNo())){
            logger.info("deleteContactsByMonitored | can not delete contact that not belong to you");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"无法删除不属于自己的联系人");
        }*/
        //contactsDao.deleteByMonitoredAndMonitor(monitoredNo,monitorNo);
        contactsDao.deleteById(deleteContacts.getMmNo());

    }

    @Override
    public void updateContactsByMonitoredAndMonitor(String monitoredNo, String monitorNo,String relationShip){
        if(monitoredNo == null || monitoredNo == "" || monitorNo == null ){
            logger.info("deleteContactsByMonitored | this para is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"修改联系人参数错误，无法删除");
        }
        contactsDao.updateByMonitoredAndMonitor(monitoredNo,monitorNo,relationShip);
    }

    @Override
    public void updateContacts(MonitoredAndMonitor contacts) {
        if(contacts == null
                || contacts.getMonitoredNo() == null || contacts.getMonitorPhone() == ""
                || contacts.getMonitoredNo() == null || contacts.getMonitoredNo() == ""
                || contacts.getRelationShip() == null || contacts.getRelationShip() == ""){
            logger.info("createContacts | this contacts is error");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"联系人信息错误，无法修改");
        }
        contactsDao.update(contacts);
    }

    @Override
    public List<MonitoredAndMonitor> getProContacts(String userNo) {
        if(userNo == null || userNo == ""){
            logger.info("getAllContacts | this userName is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"用户名错误，无法进行查询");
        }
        List<MonitoredAndMonitor> contactsList = contactsDao.findProContacts(userNo);
        if(contactsList.size() == 0){
            logger.info("getAllContacts | do not exists contacts");
            throw new AppRTException(AppExCode.CON_CONTACTS_NOT_EXISTS,"不存在该用户对应的联系人");
        }
        return contactsList;
    }

    @Override
    public List<MonitoredAndMonitor> getHelpContacts(String userNo) {
        if(userNo == null || userNo == ""){
            logger.info("getAllContacts | this userName is null");
            throw new AppRTException(AppExCode.CON_PARA_NULL,"用户名错误，无法进行查询");
        }
        List<MonitoredAndMonitor> contactsList = contactsDao.findProContacts(userNo);
        if(contactsList.size() == 0){
            logger.info("getAllContacts | do not exists contacts");
            throw new AppRTException(AppExCode.CON_CONTACTS_NOT_EXISTS,"不存在该用户对应的联系人");
        }
        return contactsList;
    }


}