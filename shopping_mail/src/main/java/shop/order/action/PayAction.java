package shop.order.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.entity.Order;
import shop.order.service.OrderService;
import shop.util.PaymentUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by near on 2016/3/5.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class PayAction extends ActionSupport {

    @Autowired
    private OrderService orderService;

    private Order order;

    private HttpServletRequest request = ServletActionContext.getRequest();

    /**
     * 支付通道编码
     */
    private String pd_FrpId;

    public String payForOrder() {
        if (order.getOid() != null) {
            // 修改订单
            Order old_order = orderService.updateOrder(order);
            // 为订单付款
            String url = orderService.payForOrder(old_order, pd_FrpId);
            try {
                ServletActionContext.getResponse().sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return NONE;
    }

    /**
     * 处理付款成功后重定向的请求
     *
     * @return
     */
    public String payCallBack() {
        // 获取返回的支付订单信息
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String hmac = request.getParameter("hmac");

        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);

        if (r6_Order != null) {
            // 验证交易的正确性
            if (isValid) {
                // 更新订单状态
                Integer state = 2;
                orderService.updateOrderState(r6_Order, state);
                if ("1".equals(r9_BType)) {
                    // 浏览器重定向
                    this.addActionMessage("支付成功! 订单编号为: " + r6_Order + " 付款金额为: " + r3_Amt);
                } else if ("2".equals(r9_BType)) {
                    /*服务器点对点，来自于易宝的通知
                    *   回复给易宝 success，如果不回复，易宝会一直通知
                    */
                    try {
                        ServletActionContext.getResponse().getWriter().print("success");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                this.addActionError("交易未能成功！");
            }
        }
        return SUCCESS;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }
}
