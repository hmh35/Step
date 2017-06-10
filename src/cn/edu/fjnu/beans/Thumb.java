package cn.edu.fjnu.beans;

import javax.persistence.*;

/**
 * Created by HMH on 2016/12/3.
 */

@Entity
@Table(name="s_thumb")
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="THUMB_ID")//点赞编号
    private Integer thumbId;

    @Column(name="SYNAMIC_ID",updatable = false)
    private Integer synamicId;//发表的朋友圈的编号

    @Column(name = "THUMBER_ID")
    private  String thumberId;//点赞者ID

    @Column(name = "THUMB_HEAD_PICTURE")
    private  String thumbHeadPicture;//点赞者头像

    @Column(name = "THUMB_COUNT")
    private Integer thumbCount;//点赞数

    public Integer getThumbId() {
        return thumbId;
    }

    public void setThumbId(Integer thumbId) {
        this.thumbId = thumbId;
    }

    public Integer getSynamicId() {return synamicId;}

    public void setSynamicId(Integer synamicId) {this.synamicId = synamicId;}

    public String getThumberId() {return thumberId;}

    public void setThumberId(String thumberId) {this.thumberId = thumberId;}

    public String getThumbHeadPicture() {return thumbHeadPicture;}

    public void setThumbHeadPicture(String thumbHeadPicture) {this.thumbHeadPicture = thumbHeadPicture;}

    public Integer getThumbCount() {return thumbCount;}

    public void setThumbCount(Integer thumbCount) {this.thumbCount = thumbCount;}
}
