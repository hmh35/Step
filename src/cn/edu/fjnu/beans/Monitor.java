package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 12:23
 * @Description: 监护人
 */
@Entity
@Table(name = "t_monitor")
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitor_no", updatable = false)
    private Integer monitorNo;

    @Column(name = "user_name", updatable = false)
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "title")
    private String title;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "identity_card_no")
    private String identityCardNo;

    @Column(name = "status")
    private Integer status;

    @Column(name = "access_right")
    private Integer accessRight;

    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    // 性别
    public static class MonitorSex {
        public static final int WOMAN = 0; // 女
        public static final int MAN = 1; // 男
    }

    // 状态
    public static class MonitorStatus {
        public static final int VALID = 0; // 可用
        public static final int INVALID = 1; // 禁用
    }

    //监护人权限
    public static class Right {
        public static final int ADMIN = 0; //管理员
        public static final int TEACHER = 1; //教师
        public static final int PARENT = 2; //家长
    }

    public Integer getMonitorNo() {
        return monitorNo;
    }

    public void setMonitorNo(Integer monitorNo) {
        this.monitorNo = monitorNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(Integer accessRight) {
        this.accessRight = accessRight;
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
