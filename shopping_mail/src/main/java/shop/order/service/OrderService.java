package shop.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.cart.model.Cart;
import shop.cart.model.CartItem;
import shop.entity.Order;
import shop.entity.OrderItem;
import shop.entity.User;
import shop.order.dao.OrderDao;
import shop.util.Page;
import shop.util.PaymentUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by near on 2016/3/4.
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public Order saveOrder(Cart cart, User user) {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setTotalMoney(cart.getTotalmoney());
        order.setState(1);
        order.setName(user.getRealName());
        order.setAddr(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUser(user);

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }
        orderDao.saveOrder(order);
        return order;
    }

    @Transactional(readOnly = true)
    public Page<Order> findOrdersByPageAndUid(Integer uid, Integer pageNums) {
        Page<Order> page = new Page<Order>();
        page.setPageNums(pageNums);
        Integer pageSize = 3;
        page.setPageSize(pageSize);
        List<Order> orders = orderDao.getOrdersByUid(uid, pageNums, pageSize);
        Integer totalCount = orderDao.getOrderCountByUid(uid).intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(orders);

        return page;
    }

    @Transactional
    public boolean deleteOrderByOid(Integer oid) {
        try {
            orderDao.deleteOrderByOid(oid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Order findOrderByOid(Integer oid) {
        return orderDao.findOrderByOid(oid);
    }

    @Transactional
    public Order updateOrder(Order order) {
        Order old_order = orderDao.findOrderByOid(order.getOid());
        old_order.setAddr(order.getAddr());
        old_order.setName(order.getName());
        old_order.setPhone(order.getPhone());

        orderDao.updateOrder(old_order);
        return old_order;
    }

    @Transactional
    public void updateOrderState(String oid, Integer state) {
        Order old_order = orderDao.findOrderByOid(Integer.parseInt(oid));
        old_order.setState(state);
        orderDao.updateOrder(old_order);
    }

    public String payForOrder(Order old_order, String _pd_FrpId) {
        /**
         * 业务类型 固定值 Buy
         */
        String p0_Cmd = "Buy";

        /**
         * 商户编号
         */
        String p1_MerId = "10001126856";

        /**
         * 商户订单号
         */
        String p2_Order = String.valueOf(old_order.getOid());

        /**
         * 支付金额
         */
        String p3_Amt = String.valueOf(0.01);
        // String p3_Amt = String.valueOf(old_order.getTotalMoney());

        /**
         * 交易币种 固定值 CNY
         */
        String p4_Cur = "CNY";

        /**
         * 商品名称  此参数如用到中文，请注意转码
         */
        String p5_Pid = "";

        /**
         * 商品种类  此参数如用到中文，请注意转码
         */
        String p6_Pcat = "";

        /**
         * 商品描述  此参数如用到中文，请注意转码
         */
        String p7_Pdesc = "";

        /**
         * 商户接收支付成功数据的地址  支付成功后易宝支付会向该地址发送两次成功通知
         */
        String p8_Url = "http://localhost:8080/pay_callback.action";

        /**
         * 送货地址  为 1: 需要用户将送货地址留在易宝支付系统;为 0: 不需要
         */
        String p9_SAF = "";

        /**
         * 商户扩展信息  此参数如用到中文，请注意转码
         */
        String pa_MP = "";

        /**
         * 支付通道编码  默认为 ”” ，到易宝支付网关.
         若不需显示易宝支付的页面，直接跳转到各银行.
         */
        String pd_FrpId = _pd_FrpId;

        /**
         * 应答机制
         *  固定值若为 1: 需要应答机制; 收到易宝支付服务器点对点支付成功通知，
         *  必须回写以”success”（无关大小写）开头的字符串，即使您收到成功通知时发现该订单已经处理过，也要正确回写”success”，
         *  否则易宝支付将认为您的系统没有收到通知，启动重发机制，直到收到”success”为止。
         */
        String pr_NeedResponse = "";

        /**
         * 商户密钥
         */
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

        /**
         * 签名数据
         */
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
                p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
                pr_NeedResponse, keyValue);

        // 将所有参数发送给 易宝指定的URL
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        return sb.toString();
    }

    @Transactional(readOnly = true)
    public Page<Order> findOrderDetailsByPage(Integer pageNums) {
        Page<Order> page = new Page<Order>();
        page.setPageNums(pageNums);
        Integer pageSize = 8;
        page.setPageSize(pageSize);
        List<Order> orders = orderDao.getOrdersDetailsByPage(pageNums, pageSize);
        Integer totalCount = orderDao.getOrderCount().intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(orders);

        return page;
    }

    @Transactional(readOnly = true)
    public List<OrderItem> findOrderItemsByOid(Integer oid) {
        return orderDao.findOrderItemsByOid(oid);
    }

}
