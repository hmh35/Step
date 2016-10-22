package cn.edu.fjnu.beans;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/16.
 */
@Entity
@Table(name = "t_user_and_activities")
public class UserAndActivities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UA_NO",updatable = false)
    private Integer uaNo;

    @Column(name = "ACT_NO")
    private Integer actNo;

    @Column(name = "CREATOR_NO")
    private String creatorNo;

    @Column(name = "MONITORED_NO")
    private String monitoredNo;

    public UserAndActivities(Integer actNo, String creatorNo, String monitoredNo) {
        this.actNo = actNo;
        this.creatorNo = creatorNo;
        this.monitoredNo = monitoredNo;
    }

    public UserAndActivities() {
    }

    public void setUaNo(Integer uaNo) {
        this.uaNo = uaNo;
    }

    public void setActNo(Integer actNo) {
        this.actNo = actNo;
    }

    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo;
    }

    public void setMonitoredNo(String monitoredNo) {
        this.monitoredNo = monitoredNo;
    }

    public Integer getUaNo() {
        return uaNo;
    }

    public Integer getActNo() {
        return actNo;
    }

    public String getCreatorNo() {
        return creatorNo;
    }

    public String getMonitoredNo() {
        return monitoredNo;
    }
}
