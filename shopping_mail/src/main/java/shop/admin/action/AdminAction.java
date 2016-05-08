package shop.admin.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.admin.entity.Administrator;
import shop.admin.service.AdminService;

import javax.servlet.http.HttpSession;

/**
 * Created by near on 2016/3/5.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Administrator> {

    @Autowired
    private AdminService adminService;

    private Administrator administrator;

    private HttpSession session = ServletActionContext.getRequest().getSession();

    public String adminLogin() {
        Administrator _administrator = (Administrator) ServletActionContext.getRequest().getSession().getAttribute("administrator");
        if (_administrator != null) {
            return "loginSuccess";
        } else {
            boolean flag = adminService.checkUser(administrator);
            if (flag) {
                session.setAttribute("administrator", administrator);
                return "loginSuccess";
            } else {
                this.addActionError("用户不存在！");
                return "loginFailure";
            }
        }
    }

    public Administrator getModel() {
        administrator = new Administrator();
        return administrator;
    }
}
