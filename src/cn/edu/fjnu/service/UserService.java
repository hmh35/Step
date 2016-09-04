package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service接口层
 */
public interface UserService {

    void saveUser(User user);

    User getUserById(Integer id);

    List<User> findAll();

    List<User> findAllByOrder(String orderBy, boolean isAsc);

    public List<User> findAllByProperty(Map<String, Object> propertyNameMap);

}
