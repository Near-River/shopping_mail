package shop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * Created by near on 2016/3/4.
 */
@Entity
@Table(name = "orders")
public class Order {

    /**
     * 订单编号
     */
    private Integer oid;

    /**
     *订单总金额
     */
    private double totalMoney;

    /**
     *订单创建时间
     */
    private Date createTime;

    /**
     *订单状态
     */
    private Integer state;

    /**
     *订单用户名
     */
    private String name;

    /**
     *订单用户手机号
     */
    private String phone;

    /**
     *订单用户地址
     */
    private String addr;

    /**
     *订单用户
     */
    private User user;

    /**
     * 订单的订单项
     */
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order() {
    }

    public Order(Integer oid, double totalMoney, Date createTime, String name, Integer state) {
        this.oid = oid;
        this.totalMoney = totalMoney;
        this.createTime = createTime;
        this.name = name;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    @Column(name = "total")
    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ordertime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", unique = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OrderBy(value = "itemid")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
