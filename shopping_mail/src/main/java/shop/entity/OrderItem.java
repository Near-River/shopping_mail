package shop.entity;

import javax.persistence.*;

/**
 * 订单项实体类
 * Created by near on 2016/3/4.
 */
@Entity
@Table(name = "orderitem")
public class OrderItem {

    /**
     * 订单项ID
     */
    private Integer itemid;

    /**
     * 订单项中商品个数
     */
    private Integer count;

    /**
     * 订单项的总金额
     */
    private double subtotal;

    /**
     * 订单项的商品信息
     */
    private Product product;

    /**
     * 订单项所属订单
     */
    private Order order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @ManyToOne
    @JoinColumn(name = "pid")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "oid", unique = true)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
