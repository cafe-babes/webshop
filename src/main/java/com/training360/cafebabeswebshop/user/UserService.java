package com.training360.cafebabeswebshop.user;

import com.training360.cafebabeswebshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {


    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    public void updateUser(long id, User user) {
        userDao.updateUser(id, user);
    }

    public long insertUserAndGetId(User user) throws DataAccessException {
        return userDao.insertUserAndGetId(user);
    }
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
