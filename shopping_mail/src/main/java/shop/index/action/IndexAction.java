package shop.index.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.category.service.CategoryService;
import shop.category.service.ProductService;
import shop.entity.Category;
import shop.entity.Product;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 访问首页的 Action
 * Created by near on 2016/2/29.
 *
 * @author Near
 * @version 1.0
 */
@Controller(value = "indexPageAction")
@Scope("prototype")
public class IndexAction extends ActionSupport {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private HttpSession session = ServletActionContext.getRequest().getSession();

    @Override
    public String execute() throws Exception {
        /**
         * 查询一级分类栏目
         */
        List<Category> categories = categoryService.findAllCategories();
        session.setAttribute("categories", categories);

        /**
         * 查询热门商品栏目
         */
        List<Product> hotProducts = productService.listHotProducts();
        // 存放到值栈中
        ActionContext.getContext().getValueStack().set("hotProducts", hotProducts);

        /**
         * 查询最新商品栏目
         */
        List<Product> newProducts = productService.listNewProducts();
        // 存放到值栈中
        ActionContext.getContext().getValueStack().set("newProducts", newProducts);

        return "index";
    }

}
