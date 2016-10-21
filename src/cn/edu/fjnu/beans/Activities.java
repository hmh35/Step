package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "t_activities")
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_no")
    private Integer actNo;

    @Column(name = "act_name")
    private String actName;

    @Column(name = "description")
    private String description;

    /**
     * 活动纬度
     */
    @Column(name = "addr_long")
    private Double addrLong;

    /**
     * 活动经度
     */
    @Column(name = "addr_lat")
    private Double addrLat;

    /**
     * 活动地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 活动时间上限
     */
    @Column(name = "aply_upplmt")
    private Date aplyUpplmt;

    /**
     * 活动时间下限
     */
    @Column(name = "aply_lowlmt")
    private Date aplyLowlmt;

    @Column(name = "act_status")
    private Integer actStatus;

    @Column(name = "creator_no")
    private String creatorNo;

    @Column(name = "create_name")
    private String createName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "act_range")
    private Integer actRange;

    @Column(name = "Time_Lab")
    private  String TimeLab;
    // 状态
    public static class ActStatus {
        public static final int VALID = 0; // 可用
        public static final int INVALID = 1; // 禁用
    }

    public String getTimeLab() {
        return TimeLab;
    }

    public void setTimeLab(String TimeLab) {
        this.TimeLab = TimeLab;
    }

    public Integer getActRange() {
        return actRange;
    }

    public void setActRange(Integer actRange) {
        this.actRange = actRange;
    }

    public Integer getActNo() {
        return actNo;
    }

    public void setActNo(Integer actNo) {
        this.actNo = actNo;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAddrLong() {
        return addrLong;
    }

    public void setAddrLong(Double addrLong) {
        this.addrLong = addrLong;
    }

    public Double getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(Double addrLat) {
        this.addrLat = addrLat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAplyUpplmt() {
        return aplyUpplmt;
    }

    public void setAplyUpplmt(Date aplyUpplmt) {
        this.aplyUpplmt = aplyUpplmt;
    }

    public Date getAplyLowlmt() {
        return aplyLowlmt;
    }

    public void setAplyLowlmt(Date aplyLowlmt) {
        this.aplyLowlmt = aplyLowlmt;
    }

    public Integer getActStatus() {
        return actStatus;
    }

    public void setActStatus(Integer actStatus) {
        this.actStatus = actStatus;
    }

    public String getCreatorNo() {
        return creatorNo;
    }

    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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
