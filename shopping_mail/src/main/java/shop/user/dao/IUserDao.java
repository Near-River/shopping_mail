package shop.user.dao;

import shop.entity.User;

import java.util.List;

/**
 * Created by near on 2016/3/1.
 *
 * @author Near
 * @version 1.0
 */
public interface IUserDao {
    User findUserByName(String userName);

    boolean findUserByCode(String code);

    User login(String username, String password);

    void saveUser(User user);

    void activeUser(String code);

    List<User> getUsersByPage(Integer pageNums, Integer pageSize);

    Long getUserCount();

    User findUserById(Integer id);

    void deleteUser(User user);

    void updateUser(User user);
}
