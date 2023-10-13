package com.chen.book.dao;

import com.chen.book.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
}
