package shop.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.category.service.CategoryService;
import shop.category.service.ProductService;
import shop.entity.CategorySecond;
import shop.entity.Product;
import shop.util.Page;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by near on 2016/3/6.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private Product product;

    private Integer pageNums;

    /**
     * 接收上传的文件
     */
    private File upload;

    private String uploadFileName;

    public Product getModel() {
        product = new Product();
        return product;
    }

    public String findAllByPage() {
        if (pageNums == null) {
            pageNums = 1;
        }
        Page<Product> pageBean = productService.findAllByPage(pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    public String addProduct() {
        List<CategorySecond> csList = categoryService.listAllCategorySecondName();
        ActionContext.getContext().getValueStack().set("csList", csList);

        return "addPage";
    }

    public String saveProduct() {
        if (upload != null) {
            String fileName = System.currentTimeMillis() + uploadFileName;
            this.uploadFile(fileName);
            product.setImage("products/upload/" + fileName);
            productService.addProduct(product);
        }

        return "saveSuccess";
    }

    public String deleteProduct() {
        Integer pid = product.getPid();
        if (pid != null) {
            product = productService.getProductById(pid);
            String path = product.getImage();
            if (path != null) {
                this.deleteFile(path);
            }
            productService.deleteProduct(product);
        }

        return "deleteSuccess";
    }

    public String editProduct() {
        Integer pid = product.getPid();
        if (pid != null) {
            List<CategorySecond> csList = categoryService.listAllCategorySecondName();
            ActionContext.getContext().getValueStack().set("csList", csList);

            product = productService.findByPid(pid);
            ActionContext.getContext().getValueStack().set("product", product);
        }

        return "editSuccess";
    }

    public String updateProduct() {
        Integer pid = product.getPid();
        if (pid != null) {
            if (upload != null) {
                String fileName = System.currentTimeMillis() + uploadFileName;
                // 上传并修改原商品的图片
                this.uploadFile(fileName);
                String path = product.getImage();
                if (path != null) {
                    this.deleteFile(path);
                }
                product.setImage("products/upload/" + fileName);
            }
            productService.updateProduct(product);
        }

        return "updateSuccess";
    }

    private void uploadFile(String fileName) {
        String realPath = ServletActionContext.getServletContext().getRealPath("/products/upload");
        File copyFile = new File(realPath, fileName);
        try {
            FileUtils.copyFile(upload, copyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(String path) {
        String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
        FileUtils.deleteQuietly(new File(realPath));
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
