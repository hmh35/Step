package cn.edu.fjnu.beans;

import com.mysql.jdbc.Blob;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HMH on 2016/11/05.
 */
@Entity
@Table(name="s_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    private  Integer commentID; //评论的编号

    @Column(name = "SYNAMIC_ID", updatable = false)
    private Integer synamicId;//朋友圈编号

    @Column(name ="OBSERVER_ID")
    private String  observerId;//评论者id

    @Column(name = "OBSERVER_NAME")
    private  String observerName;//评论者昵称

    @Column(name = "RECEIVER_ID")
    private String  receiverId;//接收者id

    @Column(name = "RECEIVER_NAME")
    private  String receiverName;//接收者昵称

    @Column(name = "COMMENT_CONTENT")
    private  String  comment_content;//评论内容

    @Column(name = "OBSERVER_HEAD_PICTURE")
    private String  observerHeadPicture;//评论者头像

    @Column(name = "COMMENT_TIME")
    private String commentTime;//评论时间

    @Column(name ="COMMENT_COUNT")
    private Integer commentCount;//评论数

    public Integer getCommentCount() {return commentCount;}

    public void setCommentCount(Integer commentCount) {this.commentCount = commentCount;}

    public Integer getCommentID() {return commentID;}

    public void setCommentID(Integer commentID) {this.commentID = commentID;}

    public Integer getSynamicId() {return synamicId;}

    public void setSynamicId(Integer synamicId) {this.synamicId = synamicId;}

    public String getObserverId() {return observerId;}

    public void setObserverId(String observerId) {this.observerId = observerId;}

    public String getObserverName() {return observerName;}

    public void setObserverName(String observerName) {this.observerName = observerName;}

    public String getReceiverId() {return receiverId;}

    public void setReceiverId(String receiverId) {this.receiverId = receiverId;}

    public String getReceiverName() {return receiverName;}

    public void setReceiverName(String receiverName) {this.receiverName = receiverName;}

    public String getComment_content() {return comment_content;}

    public void setComment_content(String comment_content) {this.comment_content = comment_content;}

    public String getObserverHeadPicture() {return observerHeadPicture;}

    public void setObserverHeadPicture(String observerHeadPicture) {this.observerHeadPicture = observerHeadPicture;}

    public String getCommentTime() {return commentTime;}

    public void setCommentTime(String commentTime) {this.commentTime = commentTime;}
}

