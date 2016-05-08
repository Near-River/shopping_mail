package shop.cart.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.cart.model.Cart;
import shop.cart.model.CartItem;
import shop.category.service.ProductService;

import javax.servlet.http.HttpSession;

/**
 * Created by near on 2016/3/4.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class CartAction extends ActionSupport {

    private Integer pid;

    private Integer quantity;

    @Autowired
    private ProductService productService;

    private HttpSession session = ServletActionContext.getRequest().getSession();

    public String addCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {  // 用户已登陆
            CartItem cartItem = new CartItem();
            cartItem.setCount(quantity);
            cartItem.setProduct(productService.getProductById(pid));
            cart.addToCart(cartItem);
            session.setAttribute("cart", cart);
        }
        return "addCart";
    }

    public String removeCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeFromCart(pid);
        return "removeCart";
    }

    public String clearCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clearCart();
        return "clearCart";
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
