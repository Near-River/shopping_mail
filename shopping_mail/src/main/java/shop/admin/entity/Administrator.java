package shop.admin.entity;

import javax.persistence.*;

/**
 * Created by near on 2016/3/5.
 */
@Entity
@Table(name = "adminuser")
public class Administrator {

    /**
     * 管理员ID
     */
    private Integer id;

    /**
     * 管理员姓名
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
