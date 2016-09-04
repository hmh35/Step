package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:10
 * @Description: 被监护人
 */
@Entity
@Table(name = "t_monitored")
public class Monitored implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitored_no", updatable = false)
    private Integer monitoredNo;

    @Column(name = "student_no", updatable = false)
    private String studentNo;

    @Column(name = "password")
    private String password;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "identity_card_no")
    private String identityCardNo;

    @Column(name = "status")
    private Integer status;

    @Column(name = "admission_time")
    private Date admissionTime;

    @Column(name = "length_of_schooling")
    private Integer lengthOfSchooling;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


    // 性别
    public static class MonitoredSex {
        public static final int WOMAN = 0; // 女
        public static final int MAN = 1; // 男
    }

    // 状态
    public static class MonitoredStatus {
        public static final int VALID = 0; // 可用
        public static final int INVALID = 1; // 禁用
    }

    public Integer getMonitoredNo() {
        return monitoredNo;
    }

    public void setMonitoredNo(Integer monitoredNo) {
        this.monitoredNo = monitoredNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Integer getLengthOfSchooling() {
        return lengthOfSchooling;
    }

    public void setLengthOfSchooling(Integer lengthOfSchooling) {
        this.lengthOfSchooling = lengthOfSchooling;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
