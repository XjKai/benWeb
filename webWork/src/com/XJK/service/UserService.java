package com.XJK.service;

import com.XJK.pojo.User;

/**
 * 定义功能接口
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    public boolean existUsername(String username);
}
