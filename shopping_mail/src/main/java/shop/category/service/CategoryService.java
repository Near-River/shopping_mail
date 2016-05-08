package shop.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.category.dao.CategoryDao;
import shop.entity.Category;
import shop.entity.CategorySecond;
import shop.util.Page;

import java.util.List;

/**
 * Created by near on 2016/3/2.
 *
 * @author Near
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> findAllCategories() {
        return categoryDao.listAll();
    }

    @Transactional
    public void addCategroy(Category category) {
        categoryDao.saveCategory(category);
    }

    public Category findCategoryByCid(Integer cid) {
        return categoryDao.findCategoryByCid(cid);
    }

    @Transactional
    public void deleteCategroy(Integer cid) {
        Category category = categoryDao.findCategoryByCid(cid);
        if (category != null) {
            categoryDao.deleteCategroy(category);
        }
    }

    @Transactional
    public void updateCategory(Category category) {
        Category old_category = categoryDao.findCategoryByCid(category.getCid());
        if (old_category != null) {
            old_category.setCname(category.getCname());
            categoryDao.updateCategroy(old_category);
        }
    }

    public Page<CategorySecond> findAllByPage(Integer pageNums) {
        Page<CategorySecond> page = new Page<CategorySecond>();
        page.setPageNums(pageNums);
        Integer pageSize = 10;
        page.setPageSize(pageSize);
        List<CategorySecond> csList = categoryDao.getCSByPage(pageNums, pageSize);
        Integer totalCount = categoryDao.getCSCountByPage().intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(csList);

        return page;
    }

    @Transactional
    public void addCatgorySecond(CategorySecond categorySecond) {
        categoryDao.saveCatgorySecond(categorySecond);
    }

    public List<Category> listAllCategoryName() {
        return categoryDao.listAllCategoryName();
    }

    @Transactional
    public void deleteCategroySecond(Integer csid) {
        CategorySecond cs = categoryDao.findCSByCsid(csid);
        if (cs != null) {
            categoryDao.deleteCategroySecond(cs);
        }
    }

    public CategorySecond findCSByCsid(Integer csid) {
        return categoryDao.findCSByCsid(csid);
    }

    @Transactional
    public void updateCategorySecond(CategorySecond categorySecond) {
        Integer cid = categorySecond.getCategory().getCid();
        if (cid != null) {
            Category category = categoryDao.findCategoryByCid(cid);
            categorySecond.setCategory(category);
            categoryDao.updateCategroySecond(categorySecond);
        }
    }

    public List<CategorySecond> listAllCategorySecondName() {
        return categoryDao.listAllCategorySecondName();
    }
}
