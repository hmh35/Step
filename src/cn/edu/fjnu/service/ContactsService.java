package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/9 18:59
 * @Description:
 */
public interface ContactsService {

    /**
     * 创建新联系人
     *
     * @param contacts
     */
    void createContacts(Contacts contacts);

    /**
     * 通过用户No（主键）获取所有联系人
     *
     * @param monitoredNo
     * @return
     */
    List<Contacts> getAllContacts(String monitoredNo, Page page);

    /**
     * 通过用户No（主键）删除对应的联系人
     *
     * @param monitoredNo
     */
    void deleteContactsByMonitored(String monitoredNo, Contacts contacts);

    /**
     * 更新联系人
     * @param contacts
     */
    void updateContactByMonitored(Contacts contacts);

}
