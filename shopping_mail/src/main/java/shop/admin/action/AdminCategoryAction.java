package shop.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.category.service.CategoryService;
import shop.entity.Category;

import java.util.List;

/**
 * Created by near on 2016/3/6.
 *
 * @author Near
 * @version 1.0
 */
@Controller(value = "adminCAction")
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

    @Autowired
    private CategoryService categoryService;

    private Category category;

    public String findAll() {
        List<Category> categoryList = categoryService.findAllCategories();
        ActionContext.getContext().getValueStack().set("categoryList", categoryList);
        return "findAll";
    }

    public String addCategory() {
        if (category != null) {
            categoryService.addCategroy(category);
            return "addSuccess";
        }
        this.addActionError("添加一级商品分类失败！");
        return "addFailure";
    }

    public String deleteCategory() {
        Integer cid = category.getCid();
        if (cid != null) {
            categoryService.deleteCategroy(cid);
            return "deleteSuccess";
        }
        this.addActionError("删除一级商品分类失败！");
        return "deleteFailure";
    }

    public String editCategory(){
        Integer cid = category.getCid();
        if (cid != null) {
            Category category = categoryService.findCategoryByCid(cid);
            ActionContext.getContext().getValueStack().set("category", category);
            return "editSuccess";
        }
        this.addActionError("修改一级商品分类失败！");
        return "editFailure";
    }

    public String updateCategory(){
        if (category != null) {
            categoryService.updateCategory(category);
            return "updateSuccess";
        }
        this.addActionError("更新一级商品分类失败！");
        return "updateFailure";
    }

    public Category getModel() {
        category = new Category();
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
