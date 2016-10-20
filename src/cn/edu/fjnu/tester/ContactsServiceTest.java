/*
package cn.edu.fjnu.tester;

import cn.edu.fjnu.beans.Contacts;
import cn.edu.fjnu.dao.base.Page;
import cn.edu.fjnu.service.ContactsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

*/
/**
 * @Author: linqiu
 * @Date: 2016/3/9 20:26
 * @Description:
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ContactsServiceTest {
    private Logger logger = LoggerFactory.getLogger(ActivitiesServiceTest.class);

    @Resource
    private ContactsService contactsService;

    @Test
    public void createContacts() {
        for (int i = 0; i < 30; i++) {
            Contacts contacts = new Contacts();
            contacts.setMonitoredNo("29");
            contacts.setContactName("唐文明"+i);
            contacts.setContactPhone("1367509381"+i);
            contacts.setContactRelation("室友"+i);
            contactsService.createContacts(contacts);
        }

    }

    @Test
    public void getAllContactsTest() {
        Page page = new Page(1,10);
        List<Contacts> contactsList = contactsService.getAllContacts("29",page);
        System.out.println(contactsList.get(0).getMonitoredNo());
    }

    @Test
    public void deleteContactsTest() {

        //contactsService.deleteContactsByMonitored("29");
    }
}
*/
