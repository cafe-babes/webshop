package com.training360.cafebabeswebshop.user;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDao userDao;
    private PasswordEncoder encoder;

    public UserService(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
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
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.updateUser(id, user);
        }
    }

    public long insertUserAndGetId(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.insertUserAndGetId(user);
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public ResultStatus<User> getUserByName(String userName) {
        try {
            User user = userDao.getUserByName(userName).get(0);
            ResultStatus<User> status = new ResultStatus<>(ResultStatusEnum.OK, "OK");
            status.set(user);
            return status;
        } catch (IndexOutOfBoundsException e) {
            return new ResultStatus<>(ResultStatusEnum.NOT_OK, "Nem található ilyen nevű felhasználó!");
        }
    }
}
