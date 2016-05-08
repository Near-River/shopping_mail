package shop.order.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.cart.model.Cart;
import shop.entity.Order;
import shop.entity.User;
import shop.order.service.OrderService;
import shop.util.Page;

import javax.servlet.http.HttpSession;

/**
 * 订单管理的 Action
 * Created by near on 2016/3/4.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport {

    @Autowired
    private OrderService orderService;

    private Integer pageNums;

    private Integer oid;

    private HttpSession session = ServletActionContext.getRequest().getSession();

    public String addOrder() {
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("existUser");
        Order order = null;
        if (cart != null && user != null) {
            order = orderService.saveOrder(cart, user);
        }
        ActionContext.getContext().getValueStack().set("order", order);
        // 清空购物车
        cart.clearCart();
        return SUCCESS;
    }

    public String findOrdersByPageAndUid() {
        User user = (User) session.getAttribute("existUser");
        if (user != null) {
            Integer uid = user.getId();
            Page<Order> pageBean = orderService.findOrdersByPageAndUid(uid, pageNums);
            ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        }
        return SUCCESS;
    }

    public String deleteOrder() {
        if (orderService.deleteOrderByOid(oid)) {
            User user = (User) session.getAttribute("existUser");
            if (pageNums == null) {
                pageNums = 1;
            }
            Page<Order> pageBean = orderService.findOrdersByPageAndUid(user.getId(), pageNums);
            ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        }
        return SUCCESS;
    }

    public String findOrderByOid() {
        Order order = orderService.findOrderByOid(oid);
        ActionContext.getContext().getValueStack().set("order", order);
        return SUCCESS;
    }

    public String updateOrderState() {
        if (oid != null) {
            Integer state = 4;
            orderService.updateOrderState(oid.toString(), state);
        }
        return SUCCESS;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

}
