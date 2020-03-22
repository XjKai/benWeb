package com.XJK.test;

import com.XJK.pojo.User;
import com.XJK.service.UserService;
import com.XJK.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService =new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"ubuntu","ubuntu","ubuntu@123456"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"root","root","123")));
    }

    @Test
    public void existUsername() {
    }
}