package com.XJK.dao.impl;

import com.XJK.dao.UserDao;
import com.XJK.pojo.User;

import java.sql.ResultSet;
import java.util.List;

/**
 * 查询数据库接口UserDao接口实现类
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
       String sql = "select id, username, password, email from t_user where username = ?";
        List<User> userList = queryUser(sql, username);
        if (userList.size() > 0 ){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password, email) values (?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        List<User> userList = queryUser(sql, username, password);
        if (userList.size() > 0 ){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public int deleteUserBuUsername(String username) {
        String sql = "delete from t_user where username = ?";
        return update(sql, username);
    }
}
