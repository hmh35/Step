package cn.edu.fjnu.beans;

import javax.persistence.*;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 13:05
 * @Description: 被监护人联系人表
 */
@Entity
@Table(name = "t_contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_no")
    private Integer contactNo;

    @Column(name = "monitored_no")
    private String monitoredNo;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_relation")
    private String contactRelation;

    // 关系
    public static class Relation {
        public static final String TEACHER = "老师";
        public static final String CLASSMATE = "同学";
        public static final String PARENTS = "父母";
        public static final String FRIEND = "朋友";
    }

    public Integer getContactNo() {
        return contactNo;
    }

    public void setContactNo(Integer contactNo) {
        this.contactNo = contactNo;
    }

    public String getMonitoredNo() {
        return monitoredNo;
    }

    public void setMonitoredNo(String monitoredNo) {
        this.monitoredNo = monitoredNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactRelation() {
        return contactRelation;
    }

    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation;
    }
}
