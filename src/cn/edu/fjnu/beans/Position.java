package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "t_position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_no")
    private Integer positionNo;

    @Column(name = "monitored_no")
    private String monitoredNo;

    @Column(name = "addr_long")
    private Double addrLong;

    @Column(name = "addr_lat")
    private Double addrLat;

    @Column(name = "address")
    private String address;

    @Column(name = "student_no", updatable = false)
    private String studentNo;

    @Column(name = "real_name", updatable = false)
    private String realName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "phone_number")
    private String telPhone;

    public Position() {
    }

    public Position(Integer positionNo, String monitoredNo, Double addrLong, Double addrLat, String address, Date createTime) {
        this.positionNo = positionNo;
        this.monitoredNo = monitoredNo;
        this.addrLong = addrLong;
        this.addrLat = addrLat;
        this.address = address;
        this.createTime = createTime;
    }
    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String phoneNumber) {
        this.telPhone = phoneNumber;
    }

    public Integer getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(Integer positionNo) {
        this.positionNo = positionNo;
    }

    public String getMonitoredNo() {
        return monitoredNo;
    }

    public void setMonitoredNo(String monitoredNo) {
        this.monitoredNo = monitoredNo;
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

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
