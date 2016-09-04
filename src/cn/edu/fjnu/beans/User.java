package cn.edu.fjnu.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: User类测试实体
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Integer no;
    @Column(name = "name")
    private String names;
    @Column(name = "age")
    private Integer age;

    @Column(name = "create_time")
    //private Timestamp createTime;
    private Date createTime;

    public User(String names) {
        this.names = names;
    }
    public User(String names,Integer age) {
        this.names = names;
        this.age = age;
    }
    public User(Integer no,String names,Integer age,Date timestamp) {
        this.no = no;
        this.names = names;
        this.age = age;
        this.createTime = timestamp;
    }

    public User() {

    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
