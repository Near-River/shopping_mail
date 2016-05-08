package shop.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品表
 * Created by near on 2016/3/3.
 */
@Entity
@Table(name = "product")
public class Product {
    /**
     * 商品ID
     */
    private Integer pid;

    /**
     * 商品名称
     */
    private String pname;

    /**
     * 商品市场价
     */
    private double market_price;

    /**
     * 商品零售价
     */
    private double shop_price;

    /**
     * 商品图片名称
     */
    private String image;

    /**
     * 商品名称
     */
    private String pdesc;

    /**
     * 商品是否为热门
     */
    private int is_hot;

    /**
     * 商品生成日期
     */
    private Date pdate;

    /**
     * 商品所属二进分类
     */
    private CategorySecond categorySecond;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    @ManyToOne
    @JoinColumn(name = "csid", unique = true)
    public CategorySecond getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(CategorySecond categorySecond) {
        this.categorySecond = categorySecond;
    }

}
