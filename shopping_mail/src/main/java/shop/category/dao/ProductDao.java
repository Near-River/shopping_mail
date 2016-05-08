package shop.category.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.entity.Product;

import java.util.List;

/**
 * Created by near on 2016/3/3.
 *
 * @author Near
 * @version 1.0
 */
@Repository
@SuppressWarnings("all")
public class ProductDao {

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public List<Product> listHotProducts() {
        Session session = getSession();
        return session.createQuery("FROM Product p WHERE p.is_hot=1 ORDER BY p.pdate DESC").setMaxResults(10).list();
    }

    public List<Product> listNewProducts() {
        Session session = getSession();
        return session.createQuery("FROM Product p ORDER BY p.pdate DESC").setMaxResults(10).list();
    }

    public Product getProductById(Integer pid) {
        Session session = getSession();
        return (Product) session.get(Product.class, pid);
    }

    public List<Product> getProductsByCid(Integer cid, Integer pageNums, Integer pageSize) {
        Session session = getSession();
        return session.createQuery("FROM Product p WHERE p.categorySecond.csid IN (SELECT cs.csid FROM CategorySecond cs WHERE cs.category.cid=:cid)")
                .setParameter("cid", cid).setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public Long getProductCountByCid(Integer cid) {
        Session session = getSession();
        return (Long) session.createQuery("SELECT COUNT(p.pid) FROM Product p WHERE p.categorySecond.csid IN (SELECT cs.csid FROM CategorySecond cs WHERE cs.category.cid=:cid)")
                .setParameter("cid", cid).uniqueResult();
    }

    public List<Product> getProductsByCsid(Integer csid, Integer pageNums, Integer pageSize) {
        Session session = getSession();
        return session.createQuery("FROM Product p WHERE p.categorySecond.csid=:csid")
                .setParameter("csid", csid).setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public Long getProductCountByCsid(Integer csid) {
        Session session = getSession();
        return (Long) session.createQuery("SELECT COUNT(p.pid) FROM Product p WHERE p.categorySecond.csid=:csid")
                .setParameter("csid", csid).uniqueResult();
    }

    public List<Product> getProductsByPage(Integer pageNums, Integer pageSize) {
        Session session = getSession();
        return session.createQuery("FROM Product p ORDER BY p.pdate DESC")
                .setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public Long getProductCount() {
        return (Long) getSession().createQuery("SELECT COUNT(p.pid) FROM Product p").uniqueResult();
    }

    public void saveProduct(Product product) {
        getSession().save(product);
    }

    public void deleteProduct(Product product) {
        getSession().delete(product);
    }

    public Product findByPid(Integer pid) {
        return (Product) getSession().get(Product.class, pid);
    }

    public void updateProduct(Product product) {
        getSession().update(product);
    }
}
