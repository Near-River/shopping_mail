package shop.user.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.entity.User;
import shop.user.dao.IUserDao;

import java.util.List;


/**
 * Created by near on 2016/3/1.
 *
 * @author Near
 * @version 1.0
 */
@Repository
@SuppressWarnings("all")
public class UserDao implements IUserDao {

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public User findUserByName(String userName) {
        String hql = "SELECT u FROM User u WHERE u.userName=:name";
        Session session = getSession();
        User user = (User) session.createQuery(hql).setParameter("name", userName).uniqueResult();
        return user;
    }

    public boolean findUserByCode(String code) {
        String hql = "SELECT u FROM User u WHERE u.activeCode=:code";
        Session session = getSession();
        User user = (User) session.createQuery(hql).setParameter("code", code).uniqueResult();
        if (user != null) {
            return true;
        }
        return false;
    }

    public User login(String username, String password) {
        String hql = "SELECT u FROM User u WHERE u.userName=:username AND u.password=:password";
        Session session = getSession();
        User user = (User) session.createQuery(hql).setParameter("username", username)
                .setParameter("password", password).uniqueResult();
        return user;
    }

    public void saveUser(User user) {
        getSession().save(user);
    }

    public void activeUser(String code) {
        String hql = "UPDATE User u SET u.state = 1, u.activeCode = null WHERE u.activeCode=:code";
        Session session = getSession();
        session.createQuery(hql).setParameter("code", code).executeUpdate();
    }

    public List<User> getUsersByPage(Integer pageNums, Integer pageSize) {
        return getSession().createQuery("FROM User u ORDER BY u.id").setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public Long getUserCount() {
        return (Long) getSession().createQuery("SELECT COUNT(u.id) FROM User u").uniqueResult();
    }

    public User findUserById(Integer id) {
        return (User) getSession().get(User.class, id);
    }

    public void updateUser(User user) {
        getSession().update(user);
    }

    public void deleteUser(User user) {
        getSession().delete(user);
    }
}
