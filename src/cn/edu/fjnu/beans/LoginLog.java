package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: HMH
 * @Date: 2016/11/20 20:07
 * @Description: 用户登录日志
 */
@Entity
@Table(name = "s_login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_no",updatable = false)
    private Integer loginLogNo;

    @Column(name = "user_name",updatable = false)
    private String userName;

    @Column(name = "access_token")
    private String accesstoken;

    @Column(name = "create_time")
    private  Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "effictive_time")
    private Date effictiveTime;

    public Integer getLoginLogNo() {
        return loginLogNo;
    }

    public void setLoginLogNo(Integer loginLogNo) {
        this.loginLogNo = loginLogNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
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

    public Date getEffictiveTime() {
        return effictiveTime;
    }

    public void setEffictiveTime(Date effictiveTime) {
        this.effictiveTime = effictiveTime;
    }
}
