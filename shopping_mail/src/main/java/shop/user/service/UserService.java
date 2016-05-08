package shop.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.entity.User;
import shop.user.dao.IUserDao;
import shop.util.MailUtils;
import shop.util.Page;
import shop.util.UUIDUtils;

import java.util.List;

/**
 * Created by near on 2016/3/1.
 *
 * @author Near
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private IUserDao userDao;

    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    public boolean findUserByCode(String code) {
        return userDao.findUserByCode(code);
    }

    @Transactional
    /**
     * 完成用户注册信息保存 + 为注册用户发送激活邮件
     */
    public void addUser(User user) {
        // 0代表用户为未激活状态，1代表用户为激活状态
        user.setState(0);
        user.setActiveCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
        userDao.saveUser(user);

        // 发送激活邮件
        MailUtils.sendMail(user.getEmail(), user.getActiveCode());
    }

    @Transactional
    public void activeUser(String code) {
        userDao.activeUser(code);
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    public Page<User> findAllByPage(Integer pageNums) {
        Page<User> page = new Page<User>();
        page.setPageNums(pageNums);
        Integer pageSize = 10;
        page.setPageSize(pageSize);
        List<User> Users = userDao.getUsersByPage(pageNums, pageSize);
        Integer totalCount = userDao.getUserCount().intValue();
        page.setTotalCount(totalCount);
        Integer pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        page.setPageCount(pageCount);
        page.setObjs(Users);

        return page;
    }

    @Transactional
    public void deleteUser(Integer uid) {
        User user = userDao.findUserById(uid);
        if (user != null) {
            userDao.deleteUser(user);
        }
    }

    public User findUserById(Integer uid) {
        return userDao.findUserById(uid);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
