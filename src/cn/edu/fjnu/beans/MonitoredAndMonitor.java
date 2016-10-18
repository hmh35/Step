package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 13:12
 * @Description: 被监护人与监护人中间表
 */
@Entity
@Table(name = "t_monitored_and_monitor")
public class MonitoredAndMonitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mm_no")
    private Integer mmNo;

    @Column(name = "monitored_user_id")
    private String monitoredNo;

    @Column(name = "monitor_phone")
    private String monitorPhone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "relation_ship")
    private String relationShip;

    @Column(name = "monitor_user_id")
    private Integer monitorUserId;

    @Column(name = "monitor_name")
    private String monitorName;

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public Integer getMmNo() {
        return mmNo;
    }

    public void setMmNo(Integer mmNo) {
        this.mmNo = mmNo;
    }

    public String getMonitoredNo() {
        return monitoredNo;
    }

    public void setMonitoredNo(String monitoredNo) {
        this.monitoredNo = monitoredNo;
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

    public String getMonitorPhone() {
        return monitorPhone;
    }

    public void setMonitorPhone(String monitorPhone) {
        this.monitorPhone = monitorPhone;
    }

    public Integer getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Integer monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }
}
