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

    @Column(name = "monitored_no")
    private String monitoredNo;

    @Column(name = "monitor_no")
    private String monitorNo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "relation_ship")
    private String relationShip;

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

    public String getMonitorNo() {
        return monitorNo;
    }

    public void setMonitorNo(String monitorNo) {
        this.monitorNo = monitorNo;
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
