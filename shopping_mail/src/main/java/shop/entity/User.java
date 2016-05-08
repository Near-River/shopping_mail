package shop.entity;

import javax.persistence.*;

/**
 * 用户表
 * Created by near on 2016/2/29.
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户账号名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户邮箱账号
     */
    private String email;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户订单地址
     */
    private String address;

    /**
     * 用户状态（是否激活）
     */
    private Integer state;

    /**
     * 激活码
     */
    private String activeCode;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "addr")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name = "code")
    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
}
