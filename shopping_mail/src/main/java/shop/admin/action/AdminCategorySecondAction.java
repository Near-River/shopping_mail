package shop.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.category.service.CategoryService;
import shop.entity.Category;
import shop.entity.CategorySecond;
import shop.util.Page;

import java.util.List;

/**
 * Created by near on 2016/3/6.
 *
 * @author Near
 * @version 1.0
 */
@Controller(value = "adminCSAction")
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

    @Autowired
    private CategoryService categoryService;

    private CategorySecond categorySecond;

    private Integer pageNums;

    public String findAllByPage() {
        if (pageNums == null) {
            pageNums = 1;
        }
        Page<CategorySecond> pageBean = categoryService.findAllByPage(pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    public String addCS() {
        List<Category> categoryList = categoryService.listAllCategoryName();
        ActionContext.getContext().getValueStack().set("categoryList", categoryList);

        return "addPage";
    }

    public String saveCS() {
        if (categorySecond != null) {
            categoryService.addCatgorySecond(categorySecond);
        }
        return "saveSuccess";
    }

    public String deleteCS() {
        Integer csid = categorySecond.getCsid();
        if (csid != null) {
            categoryService.deleteCategroySecond(csid);
        }
        return "deleteSuccess";
    }

    public String editCS() {
        Integer csid = categorySecond.getCsid();
        if (csid != null) {
            List<Category> categoryList = categoryService.listAllCategoryName();
            ActionContext.getContext().getValueStack().set("categoryList", categoryList);

            categorySecond = categoryService.findCSByCsid(csid);
            ActionContext.getContext().getValueStack().set("categorySecond", categorySecond);
        }
        return "editSuccess";
    }

    public String updateCS() {
        if (categorySecond != null) {
            categoryService.updateCategorySecond(categorySecond);
        }
        return "updateSuccess";
    }

    public CategorySecond getModel() {
        categorySecond = new CategorySecond();
        return categorySecond;
    }

    public void setCategorySecond(CategorySecond categorySecond) {
        this.categorySecond = categorySecond;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

}
