package com.training360.cafebabeswebshop.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {


    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
