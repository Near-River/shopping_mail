package shop.category.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.category.service.CategoryService;
import shop.category.service.ProductService;
import shop.entity.Product;
import shop.util.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by near on 2016/3/3.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    private Product product;

    private Integer cid;

    private Integer csid;

    private Integer pageNums;

    private HttpServletRequest request = ServletActionContext.getRequest();

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public String findProductById() {
        return SUCCESS;
    }

    public String findProductsByPageAndCid() {
        Page<Product> pageBean = productService.findProductsByPageAndCid(cid, pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    public String findProductsByPageAndCsid() {
        Page<Product> pageBean = productService.findProductsByPageAndCsid(csid, pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    public Product getModel() {
        String pid = request.getParameter("pid");
        if (pid == null) {
            product = new Product();
        } else {
            product = productService.getProductById(Integer.parseInt(pid));
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }
}
