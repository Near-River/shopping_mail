package shop.admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.admin.entity.Administrator;

/**
 * Created by near on 2016/3/5.
 *
 * @author Near
 * @version 1.0
 */
@Repository
@SuppressWarnings("all")
public class AdminDao {

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public boolean findUser(Administrator administrator) {
        String hql = "FROM Administrator AS a WHERE a.username=:username AND a.password=:password";
        Session session = getSession();
        Administrator admin = (Administrator) session.createQuery(hql)
                .setParameter("username", administrator.getUsername())
                .setParameter("password", administrator.getPassword())
                .uniqueResult();
        if (admin == null) {
            return false;
        }
        return true;
    }
}
