package shop.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.category.dao.ProductDao;
import shop.entity.CategorySecond;
import shop.entity.Product;
import shop.util.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by near on 2016/3/3.
 *
 * @author Near
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryService categoryService;

    public List<Product> listHotProducts() {
        return productDao.listHotProducts();
    }

    public List<Product> listNewProducts() {
        return productDao.listNewProducts();
    }

    public Product getProductById(Integer pid) {
        return productDao.getProductById(pid);
    }

    public Page<Product> findProductsByPageAndCid(Integer cid, Integer pageNums) {
        Page<Product> page = new Page<Product>();
        page.setPageNums(pageNums);
        Integer pageSize = 12;
        page.setPageSize(pageSize);
        List<Product> products = productDao.getProductsByCid(cid, pageNums, pageSize);
        Integer totalCount = productDao.getProductCountByCid(cid).intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(products);

        return page;
    }

    public Page<Product> findProductsByPageAndCsid(Integer csid, Integer pageNums) {
        Page<Product> page = new Page<Product>();
        page.setPageNums(pageNums);
        Integer pageSize = 12;
        page.setPageSize(pageSize);
        List<Product> products = productDao.getProductsByCsid(csid, pageNums, pageSize);
        Integer totalCount = productDao.getProductCountByCsid(csid).intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(products);

        return page;
    }

    public Page<Product> findAllByPage(Integer pageNums) {
        Page<Product> page = new Page<Product>();
        page.setPageNums(pageNums);
        Integer pageSize = 10;
        page.setPageSize(pageSize);
        List<Product> products = productDao.getProductsByPage(pageNums, pageSize);
        Integer totalCount = productDao.getProductCount().intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(products);

        return page;
    }

    @Transactional
    public void addProduct(Product product) {
        product.setPdate(new Date());
        productDao.saveProduct(product);
    }

    @Transactional
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    public Product findByPid(Integer pid) {
        return productDao.findByPid(pid);
    }

    @Transactional
    public void updateProduct(Product product) {
        Integer csid = product.getCategorySecond().getCsid();
        if (csid != null) {
            CategorySecond cs = categoryService.findCSByCsid(csid);
            product.setCategorySecond(cs);
        }
        productDao.updateProduct(product);
    }
}
