package shop.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by near on 2016/2/29.
 */
@Entity
@Table(name = "categorysecond")
public class CategorySecond {
    /**
     * 二级商品分类编号
     */
    private Integer csid;

    /**
     * 二级商品分类名称
     */
    private String csname;

    /**
     * 所属一级商品分类
     */
    private Category category;

    private List<Product> products;

    public CategorySecond() {
    }

    public CategorySecond(Integer csid, String csname) {
        this.csid = csid;
        this.csname = csname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    @ManyToOne
    @JoinColumn(name = "cid", unique = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "categorySecond", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
