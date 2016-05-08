package shop.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * 一级商品分类表
 * Created by near on 2016/2/29.
 */
@Entity
@Table(name = "category")
public class Category {
    /**
     * 一级商品分类ID
     */
    private Integer cid;

    /**
     * 一级商品分类名称
     */
    private String cname;

    /**
     * 所有二级子商品分类
     */
    private List<CategorySecond> categorySeconds;

    public Category() {
    }

    public Category(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @OrderBy(value = "csid")
    public List<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(List<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

}
