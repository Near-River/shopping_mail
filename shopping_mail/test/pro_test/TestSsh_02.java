package pro_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shop.category.service.CategoryService;
import shop.category.service.ProductService;
import shop.entity.Category;
import shop.entity.Product;
import shop.order.service.OrderService;
import shop.user.service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * IOC: Inverse of Control
 * DI: Dependency Injection
 * <p>
 * created by near on 2016/3/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSsh_02 extends AbstractJUnit4SpringContextTests {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Test
    public void testFindAll() {
        System.out.println();
        for (Category category : categoryService.findAllCategories()) {
            System.out.println(category);
        }
    }

    @Test
    public void test2() {
        System.out.println();
        for (Product product : productService.listHotProducts()) {
            System.out.println(product.getPname());
        }
    }

    @Test
    public void test3() {
        System.out.println();
        Product product = productService.getProductById(76);
        System.out.println(product);
    }

    @Test
    public void test4() {
        System.out.println();
        // productService.findProductsByPageAndCid(1, 1);
        productService.findProductsByPageAndCsid(1, 1);
    }

    @Test
    public void testFileDelete() {
        String path = "D:\\IdeaProjects\\shop\\src\\main\\webapp\\products\\upload\\9.jpg";
        File file = new File(path);
        file.delete();
    }

    @Test
    public void test5() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> newList = list.subList(2, 8);
        System.out.println(newList.get(0));
        System.out.println(newList.size());
    }
}