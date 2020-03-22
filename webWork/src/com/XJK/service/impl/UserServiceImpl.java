package com.XJK.service.impl;

import com.XJK.dao.UserDao;
import com.XJK.dao.impl.UserDaoImpl;
import com.XJK.pojo.User;
import com.XJK.service.UserService;

/**
 * 实现UserService功能
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
            userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username) != null){
            return true;
        }
        return false;
    }
}
