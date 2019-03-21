package com.training360.cafebabeswebshop.user;

import com.training360.cafebabeswebshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateUser(long id, Product product) {
        userDao.updateUser(id, product);
    }
}
