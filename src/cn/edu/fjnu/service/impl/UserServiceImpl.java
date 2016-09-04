package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service实现层
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return (User) userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllByOrder(String orderBy, boolean isAsc) {
        return userDao.findAllByOrder("id", isAsc);
    }

    @Override
    public List<User> findAllByProperty(Map<String,Object> propertyNameMap) {
        return userDao.findAllByProperty(propertyNameMap);
    }
}
