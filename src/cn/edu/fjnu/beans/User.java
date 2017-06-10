package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: HMH
 * @Date: 2016/11/10 10：10
 * @Description: User类测试实体
 */
@Entity
@Table(name = "s_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID",updatable = false)
    private Integer userId;

    @Column(name="USER_HEAD_PICTURE")
    private  String userHeadPicture;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_AGE")
    private Integer userAge;

    @Column(name = "USER_SEX")
    private String userSex;

    @Column(name = "USER_HEIGHT")
    private String userHeight;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @Column(name="USER_WEIGHT")
    private  String userWeight;

    public User() {
    }

    public User(String userHeadPicture, String phoneNum, String userPwd, String userName, Integer userAge, String userSex, String userHeight, Date createTime, String channelId, String userWeight) {
        this.userHeadPicture = userHeadPicture;
        this.phoneNum = phoneNum;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
        this.userHeight = userHeight;
        this.createTime = createTime;
        this.channelId = channelId;
        this.userWeight = userWeight;
    }

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public String getUserHeadPicture() {return userHeadPicture;}

    public void setUserHeadPicture(String userHeadPicture) {this.userHeadPicture = userHeadPicture;}

    public String getPhoneNum() {return phoneNum;}

    public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}

    public String getUserPwd() {return userPwd;}

    public void setUserPwd(String userPwd) {this.userPwd = userPwd;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public Integer getUserAge() {return userAge;}

    public void setUserAge(Integer userAge) {this.userAge = userAge;}

    public String getUserSex() {return userSex;}

    public void setUserSex(String userSex) {this.userSex = userSex;}

    public String getUserHeight() {return userHeight;}

    public void setUserHeight(String userHeight) {this.userHeight = userHeight;}

    public Date getCreateTime() {return createTime;}

    public void setCreateTime(Date createTime) {this.createTime = createTime;}

    public String getChannelId() {return channelId;}

    public void setChannelId(String channelId) {this.channelId = channelId;}

    public String getUserWeight() {return userWeight;}

    public void setUserWeight(String userWeight) {this.userWeight = userWeight;}
}
