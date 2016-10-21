package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "t_mon_act")
public class MonAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mon_act_no")
    private Integer monActNo;

    @Column(name = "act_no")
    private String actNo;

    @Column(name = "monitored_no")
    private String monitoredNo;

    @Column(name = "monitor_no")
    private String monitorNo;

    @Column(name = "creat_time")
    private Date createTime;

    public Integer getMonActNo() {
        return monActNo;
    }

    public void setMonActNo(Integer monActNo) {
        this.monActNo = monActNo;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
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
}
