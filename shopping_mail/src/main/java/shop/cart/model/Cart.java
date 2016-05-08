package shop.cart.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车实体类
 * <p>
 * Created by near on 2016/3/4.
 */
public class Cart {

    /**
     * 总金额
     */
    private double totalmoney;

    /**
     * 购物项集合
     */
    private Map<Integer, CartItem> cartItemMap = new LinkedHashMap<Integer, CartItem>();

    private Collection<CartItem> cartItems;

    public double getTotalmoney() {
        return totalmoney;
    }

    public Collection<CartItem> getCartItems() {
        return cartItemMap.values();
    }

    /* 业务方法 */
    public void addToCart(CartItem cartItem) {
        Integer pid = cartItem.getProduct().getPid();
        Integer count = cartItem.getCount();
        CartItem _cartItem = null;
        // 若购物车中已经存在该购物项
        if (cartItemMap.containsKey(pid)) {
            _cartItem = cartItemMap.get(pid);
            _cartItem.setCount(_cartItem.getCount() + count);
        } else {
            _cartItem = cartItem;
        }
        cartItemMap.put(pid, _cartItem);
        this.totalmoney += cartItem.getSubtotal();
    }

    public void removeFromCart(Integer pid) {
        if (cartItemMap.containsKey(pid)){
            CartItem cartItem = cartItemMap.remove(pid);
            this.totalmoney -= cartItem.getSubtotal();
        }
    }

    public void clearCart() {
        cartItemMap.clear();
        this.totalmoney = 0.0;
    }
}
