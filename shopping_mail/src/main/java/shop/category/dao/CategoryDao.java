package shop.category.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.entity.Category;
import shop.entity.CategorySecond;

import java.util.List;

/**
 * Created by near on 2016/3/2.
 *
 * @author Near
 * @version 1.0
 */
@Repository
@SuppressWarnings("all")
public class CategoryDao {

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        return factory.getCurrentSession();
    }

    public List<Category> listAll() {
        Session session = getSession();
        return session.createQuery("FROM Category").list();
    }

    public void saveCategory(Category category) {
        getSession().saveOrUpdate(category);
    }

    public Category findCategoryByCid(Integer cid) {
        return (Category) getSession().get(Category.class, cid);
    }

    public void deleteCategroy(Category category) {
        getSession().delete(category);
    }

    public void updateCategroy(Category category) {
        getSession().update(category);
    }

    public List<CategorySecond> getCSByPage(Integer pageNums, Integer pageSize) {
        Session session = getSession();
        return session.createQuery("FROM CategorySecond cs ORDER BY cs.csid DESC")
                .setFirstResult((pageNums - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public Long getCSCountByPage() {
        return (Long) getSession().createQuery("SELECT COUNT(cs.csid) FROM CategorySecond AS cs").uniqueResult();
    }

    public void saveCatgorySecond(CategorySecond categorySecond) {
        getSession().saveOrUpdate(categorySecond);
    }

    public List<Category> listAllCategoryName() {
        return getSession().createQuery("SELECT new Category(c.cid, c.cname) FROM Category c ORDER BY c.cid").list();
    }

    public CategorySecond findCSByCsid(Integer csid) {
        return (CategorySecond) getSession().get(CategorySecond.class, csid);
    }

    public void deleteCategroySecond(CategorySecond cs) {
        getSession().delete(cs);
    }

    public void updateCategroySecond(CategorySecond new_cs) {
        getSession().update(new_cs);
    }

    public List<CategorySecond> listAllCategorySecondName() {
        return getSession().createQuery("SELECT new CategorySecond(cs.csid, cs.csname) FROM CategorySecond cs ORDER BY cs.csid").list();
    }
}
