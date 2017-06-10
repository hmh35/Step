package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HMH on 2016/12/10.
 */
@Entity
@Table(name="s_linkdata")
public class NewsEntity implements Serializable{

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LINK_ID")
    private  Integer linkId;

    @Column(name = "LINK_HREF")
    private String linkHref;//链接的地址

    @Column(name = "LINK_TEXT")
    private String linkText;//链接的标题

    @Column(name = "LINK_PIC")
    private String linkPic;//图片SRC

    @Column(name = "TIME")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkPic() {
        return linkPic;
    }

    public void setLinkPic(String linkPic) {
        this.linkPic = linkPic;
    }
}

