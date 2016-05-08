package shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.admin.dao.AdminDao;
import shop.admin.entity.Administrator;

/**
 * Created by near on 2016/3/5.
 *
 * @author Near
 * @version 1.0
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Transactional(readOnly = true)
    public boolean checkUser(Administrator administrator) {
        return adminDao.findUser(administrator);
    }
}
