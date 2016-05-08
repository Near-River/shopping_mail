package shop.admin.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import shop.admin.entity.Administrator;

/**
 * 后台权限管理的拦截器类
 * <p>
 * Created by near on 2016/3/7.
 *
 * @author Near
 * @version 1.0
 */
public class PowerInteceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Administrator administrator = (Administrator) ServletActionContext.getRequest().getSession().getAttribute("administrator");
        // 判断用户是否登录
        if (administrator == null) {
            // 获得当前访问的 Action
            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
            actionSupport.addActionError("请先登录！");
            return "loginFailure";
        } else {
            invocation.invoke();
            return null;
        }
    }

}
