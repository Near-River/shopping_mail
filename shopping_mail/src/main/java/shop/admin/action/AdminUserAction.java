package shop.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.entity.User;
import shop.user.service.UserService;
import shop.util.Page;

/**
 * Created by near on 2016/3/7.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements ModelDriven<User> {

    @Autowired
    private UserService userService;

    private User user;

    private Integer pageNums;

    public String findAllByPage() {
        if (pageNums == null) {
            pageNums = 1;
        }
        Page<User> pageBean = userService.findAllByPage(pageNums);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);

        return "findAllByPage";
    }

    public String deleteUser() {
        Integer uid = user.getId();
        if (uid != null) {
            userService.deleteUser(uid);
        }

        return "deleteUserSuccess";
    }

    public String editUser() {
        Integer uid = user.getId();
        if (uid != null) {
            user = userService.findUserById(uid);
            ActionContext.getContext().getValueStack().set("editUser", user);
        }

        return "editUserSuccess";
    }

    public String updateUser() {
        if (user != null) {
            userService.updateUser(user);
        }

        return "updateUserSuccess";
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

    public User getModel() {
        user = new User();
        return user;
    }
}
