package cn.edu.fjnu.beans;

import javax.persistence.*;

/**
 * Created by HMH on 2016/11/26.
 */

@Entity
@Table(name="s_user_and_ring")
public class UserAndRing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UR_NO",updatable = false)
    private Integer urNo;

    @Column(name = "RING_NO")
    private Integer ringNo;

    @Column(name = "CREATOR_NO")
    private String creatorNo;

    @Column(name="THUMB_NO")
    private String thumnNo;

    @Column(name="REVIEWER_NO")
    private  String reviererNo;

    public UserAndRing(Integer ringNo, String creatorNo, String thumnNo, String reviererNo) {
        this.ringNo = ringNo;
        this.creatorNo = creatorNo;
        this.thumnNo = thumnNo;
        this.reviererNo = reviererNo;
    }

    public String getThumnNo() {return thumnNo;}

    public void setThumnNo(String thumnNo) {this.thumnNo = thumnNo;}

    public String getReviererNo() {return reviererNo;}

    public void setReviererNo(String reviererNo) {this.reviererNo = reviererNo;}

    public Integer getUrNo() {return urNo;}

    public void setUrNo(Integer urNo) {
        this.urNo = urNo;
    }

    public Integer getRingNo() {
        return ringNo;
    }

    public void setRingNo(Integer ringNo) {
        this.ringNo = ringNo;
    }

    public String getCreatorNo() {
        return creatorNo;
    }

    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo;
    }

    public UserAndRing(){}

}
