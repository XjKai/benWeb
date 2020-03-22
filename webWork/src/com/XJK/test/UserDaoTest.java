package com.XJK.test;

import com.XJK.dao.UserDao;
import com.XJK.dao.impl.UserDaoImpl;
import com.XJK.pojo.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;


/**
 * 测试类
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() throws SQLException {

        System.out.println(userDao.queryUserByUsername("root"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"xjk","xjk","522697031@qq.com")));

    }

    @Test
    public void queryUserByUsernameAndPassword() {

        System.out.println(userDao.queryUserByUsernameAndPassword("root","root"));
    }
}