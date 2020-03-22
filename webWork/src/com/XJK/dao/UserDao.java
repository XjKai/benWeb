package com.XJK.dao;

import com.XJK.pojo.User;

import java.sql.ResultSet;
import java.util.List;

/**
 * 接口
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public  int saveUser(User user);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名删除用户
     * @param username
     * @return
     */
    public int deleteUserBuUsername(String username);

}
