package shop.order.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.entity.Order;
import shop.entity.OrderItem;

import java.util.List;

/**
 * Created by near on 2016/3/4.
 */
@Repository
@SuppressWarnings("all")
public class OrderDao {

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public void saveOrder(Order order) {
        getSession().save(order);
    }

    public List<Order> getOrdersByUid(Integer uid, Integer pageNums, Integer pageSize) {
        Session session = getSession();
        return session.createQuery("FROM Order o WHERE o.user.id=:uid ORDER BY o.createTime DESC")
                .setParameter("uid", uid).setFirstResult((pageNums - 1) * pageSize)
                .setMaxResults(pageSize).list();
    }

    public Long getOrderCountByUid(Integer uid) {
        Session session = getSession();
        return (Long) session.createQuery("SELECT COUNT(o.oid) FROM Order o WHERE o.user.id=:uid")
                .setParameter("uid", uid).uniqueResult();
    }

    public void deleteOrderByOid(Integer oid) {
        Session session = getSession();
        Order order = (Order) session.get(Order.class, oid);
        session.delete(order);
    }

    public Order findOrderByOid(Integer oid) {
        return (Order) getSession().get(Order.class, oid);
    }

    public void updateOrder(Order old_order) {
        getSession().saveOrUpdate(old_order);
    }

    public List<Order> getOrdersDetailsByPage(Integer pageNums, Integer pageSize) {
        /*
        List<Order> orderList = getSession().createQuery("SELECT new Order(o.oid, o.totalMoney, o.createTime, o.name, o.state) FROM Order o ORDER BY o.createTime")
                .setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
        */
        List<Order> orderList = getSession().createQuery("FROM Order o ORDER BY o.createTime")
                .setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
        return orderList;
        /*
        List<Order> orderList = getSession().createQuery("SELECT new Order(o.oid, o.totalMoney, o.createTime, o.name, o.state) FROM Order o").list();
        Integer size = orderList.size();
        Integer start = (pageNums - 1) * pageSize;
        List<Order> newOrderList = null;
        if (size <= start) {
            return null;
        }
        if (size >= pageSize + start) {
            newOrderList = orderList.subList(start, pageSize);
        } else {
            newOrderList = orderList.subList(start, size - start);
        }
        return newOrderList;
        */
    }

    public Long getOrderCount() {
        return (Long) getSession().createQuery("SELECT COUNT(o.oid) FROM Order o").uniqueResult();
    }

    public List<OrderItem> findOrderItemsByOid(Integer oid) {
        return getSession().createQuery("FROM OrderItem item WHERE item.order.oid=:oid").setParameter("oid", oid).list();
    }
}
