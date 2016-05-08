package shop.cart.model;

import shop.entity.Product;

/**
 * 购物项实体类
 * <p>
 * Created by near on 2016/3/4.
 */
public class CartItem {

    /**
     * 商品
     */
    private Product product;

    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 购物小计
     */
    private double subtotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getSubtotal() {
        return product.getShop_price() * count;
    }

}
