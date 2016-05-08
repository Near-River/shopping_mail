package pro_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shop.entity.User;
import shop.index.action.IndexAction;
import shop.user.service.UserService;
import shop.util.MailUtils;
import shop.util.UUIDUtils;

import javax.annotation.Resource;

/**
 * IOC: Inverse of Control
 * DI: Dependency Injection
 * <p>
 * created by near on 2016/2/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSsh_01 extends AbstractJUnit4SpringContextTests {

    @Test
    public void test() {
        IndexAction indexAction = new IndexAction();
        IndexAction indexAction2 = new IndexAction();

        System.out.println(indexAction.hashCode());
        System.out.println(indexAction2.hashCode());
    }

    @Resource
    private UserService userService;

    @Test
    public void test2() {
        User user = userService.findUserByName("aaa");
        System.out.println(user.getRealName());
    }

    @Test
    public void test3() {
        String str = UUIDUtils.getUUID();
        System.out.println();
        System.out.println(str);

        User user = new User();
        user.setUserName("Near");
        user.setPassword("abc");
        user.setRealName("杨萧");
        user.setAddress("苏州大学");
        user.setPhone("18862110936");
        user.setEmail("798563420@qq.com");

        userService.addUser(user);
    }

    @Test
    public void testMail(){
        MailUtils.sendMail("yangxiaonear@sina.com", UUIDUtils.getUUID() + UUIDUtils.getUUID());
    }

    @Test
    public void testActive(){
        boolean flag = userService.findUserByCode("123");
        System.out.println();
        System.out.println(flag);
        // userService.activeUser("abc");
    }
}