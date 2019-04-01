package com.training360.cafebabeswebshop.user;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void updateUser(long id, User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            userDao.updateUserWithoutPassword(id, user);
        } else {
            user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
            userDao.updateUser(id, user);
        }
    }

    public long insertUserAndGetId(User user) throws DataAccessException {
        user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
        return userDao.insertUserAndGetId(user);
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
