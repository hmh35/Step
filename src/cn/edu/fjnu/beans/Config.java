package cn.edu.fjnu.beans;

import javax.persistence.*;


@Entity
@Table(name = "t_config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_no",updatable = false)
    private Integer confNo;

    @Column(name = "user_name",updatable = false)
    private String userName;

    /**
     * 是否后台自动开启定位
     */
    @Column(name = "is_monitored")
    private Integer isMonitored;

    /**
     * 是否预警
     */
    @Column(name = "is_warning")
    private Integer isWarning;


    /**
     * 一键呼救是否播放铃声
     */
    @Column(name = "is_alarm")
    private Integer isAlarm;

    // 设置项状态
    public static class Status {
        public static final int VALID = 0; // 可用
        public static final int INVALID = 1; // 禁用
    }

    public Integer getConfNo() {
        return confNo;
    }

    public void setConfNo(Integer confNo) {
        this.confNo = confNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsMonitored() {
        return isMonitored;
    }

    public void setIsMonitored(Integer isMonitored) {
        this.isMonitored = isMonitored;
    }

    public Integer getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Integer isWarning) {
        this.isWarning = isWarning;
    }

    public Integer getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }
}
