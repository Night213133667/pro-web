package com.chen.book.service.impl;

import com.chen.book.dao.UserDAO;
import com.chen.book.pojo.User;
import com.chen.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }
}
