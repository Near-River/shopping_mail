package shop.user.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.cart.model.Cart;
import shop.entity.User;
import shop.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问首页的 Action
 * Created by near on 2016/3/1.
 *
 * @author Near
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

    private User user;

    private String checkcode;

    private HttpServletRequest request = ServletActionContext.getRequest();

    @Autowired
    private UserService userService;

    /**
     * 执行注册页面的跳转
     *
     * @return
     */
    public String registerPage() {
        return "registerPage";
    }

    /**
     * 执行登陆页面的跳转
     *
     * @return
     */
    public String loginPage() {
        return "loginPage";
    }

    /**
     * 处理AJAX异步请求（根据用户名查询用户是否存在）
     *
     * @return
     * @throws IOException
     */
    public String findByName() throws IOException {
        String userName = user.getUserName();
        User new_user = userService.findUserByName(userName);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");

        if (new_user != null) {
            response.getWriter().println("<font color='red'>用户名已存在</font");
        } else {
            response.getWriter().println("<font color='green'>用户名可用</font");
        }

        return NONE;
    }

    /**
     * 处理用户注册请求
     *
     * @return
     */
    public String register() {
        String check_code = (String) request.getSession().getAttribute("check_code");
        if (!check_code.equalsIgnoreCase(checkcode)) {
            this.addActionError("验证码不一致！");
            return "registerFailure";
        }
        userService.addUser(user);
        return "register";
    }

    /**
     * 处理用户账号激活
     *
     * @return
     */
    public String active() {
        String code = request.getParameter("code");
        String register_result = "";

        if (code == null) {
            register_result = "请及时查收邮件，以完成账号激活！";
        } else {
            if (userService.findUserByCode(code)) {
                userService.activeUser(code);
                register_result = "账号激活，恭喜您注册成功！";
            } else {
                register_result = "激活码已失效，请重新注册！";
            }
        }
        request.setAttribute("register_result", register_result);
        return "msg";
    }

    /**
     * 处理用户登陆请求
     *
     * @return
     */
    public String login() {
        User login_user = userService.login(user.getUserName(), user.getPassword());
        if (login_user != null) {
            request.getSession().setAttribute("existUser", login_user);
            // 为登录用户设置购物车
            request.getSession().setAttribute("cart", new Cart());
            return "loginSuccess";
        }
        this.addActionError("用户不存在！");
        return "loginFailure";
    }

    /**
     * 处理用户退出请求
     *
     * @return
     */
    public String logout() {
        request.getSession().invalidate();
        return "logoutSuccess";
    }

   /* public User getUser() {
        return user;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
