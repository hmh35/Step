package cn.edu.fjnu.beans;

import com.mysql.jdbc.Blob;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by HMH on 2016/11/22.
 */
@Entity
@Table(name="s_synamic")
public class Synamic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RING_ID",updatable = false)
    private Integer ringId;//发表的朋友圈的编号

    @Column(name="CREATOR_NO")
    private String creatorNo;//发表人NO

    @Column(name = "CREATOR_NAME")
    private String creatorName;//发表人名字

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="USER_HEAD_PICTURE")
    private  String userHeadPicture;//头像

    @Column(name="PICTURE")
    private String picture;//发表的动态中的图片

    @Column(name="REVIEW_ID")
    private  Integer reviewId;//评论的条数

    @Column(name="THUMB")
    private Integer thumb;//点赞数

   // @Column(name="THUMB_PICTURE")
   // private Blob thumbPicture;//点赞头像
    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="RING_STATUS")
    private  Integer ringStatus;

    // 状态
    public static class ringStatus {
        public static final int VALID = 0; // 可用
        public static final int INVALID = 1; // 禁用
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Integer getRingStatus() {return ringStatus;}

    public void setRingStatus(Integer ringStatus) {this.ringStatus = ringStatus;}

    public Integer getRingId() {return ringId;}

    public void setRingId(Integer ringId) {this.ringId = ringId;}

    public String getCreatorNo() {return creatorNo;}

    public void setCreatorNo(String creatorNo) {this.creatorNo = creatorNo;}

    public String getCreatorName() {return creatorName;}

    public void setCreatorName(String creatorName) {this.creatorName = creatorName;}

    public String getUserHeadPicture() {return userHeadPicture;}

    public void setUserHeadPicture(String userHeadPicture) {this.userHeadPicture = userHeadPicture;}

    public String getPicture() {return picture;}

    public void setPicture(String picture) {this.picture = picture;}

    public Integer getReviewId() {return reviewId;}

    public void setReviewId(Integer reviewId) {this.reviewId = reviewId;}

    public Integer getThumb() {return thumb;}

    public void setThumb(Integer thumb) {this.thumb = thumb;}

    public Date getCreateTime() {return createTime;}

    public void setCreateTime(Date createTime) {this.createTime = createTime;}
}
