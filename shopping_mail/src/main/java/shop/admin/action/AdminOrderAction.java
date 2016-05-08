package shop.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.entity.Order;
import shop.entity.OrderItem;
import shop.order.service.OrderService;
import shop.util.Page;

import java.util.List;

/**
 * Created by near on 2016/3/7.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

    @Autowired
    private OrderService orderService;

    private Order order;

    private Integer pageNums;

    public String findAllByPage() {
        if (pageNums == null) {
            pageNums = 1;
        }
        Page<Order> pageBean = orderService.findOrderDetailsByPage(pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);

        return "findAllByPage";
    }

    public String findOrderItemsByOid() {
        Integer oid = order.getOid();
        if (oid != null) {
            List<OrderItem> orderItemList = orderService.findOrderItemsByOid(oid);
            ActionContext.getContext().getValueStack().set("orderItemList", orderItemList);
        }
        return "findOrderItemsByOid";
    }

    public String updateState(){
        Integer oid = order.getOid();
        if (oid != null) {
            Integer state = 3;
            orderService.updateOrderState(oid.toString(), state);
        }
        return "updateState";
    }
    /*
    public String updateProduct() {
        Integer pid = product.getPid();
        if (pid != null) {
            productService.updateProduct(product);
        }
        return "updateSuccess";
    }*/


    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

    public Order getModel() {
        order = new Order();
        return order;
    }
}
