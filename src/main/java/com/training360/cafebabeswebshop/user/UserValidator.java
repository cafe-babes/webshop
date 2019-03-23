package com.training360.cafebabeswebshop.user;

import java.util.List;

public class UserValidator {


    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean userCanBeSaved(User user) {
        if (nameIsNotEmptyOrNull(user.getName()) && passwordIsNotEmptyOrNull(user.getPassword()) &&
                userIsNotRegisteredWithThisNameYet(user.getName())) {
            return true;
        }
        return false;
    }

    public boolean userCanBeUpdated(User user) {
        if (nameIsNotEmptyOrNull(user.getName()) && passwordIsNotEmptyOrNull(user.getPassword()) &&
                userIsNotRegisteredWithThisNameYet(user.getName())) {
            return true;
        }
        return false;
    }

    private boolean nameIsNotEmptyOrNull(String name) {
        return name != null && !name.trim().equals("");
    }

    private boolean passwordIsNotEmptyOrNull(String pass) {
        return pass != null && !pass.trim().equals("");
    }

    private boolean userIsNotRegisteredWithThisNameYet(String newUsername) {
        List<User> registeredUsers = userService.listUsers();
        for (User reguser : registeredUsers) {
            if (newUsername.equals(reguser.getName())) {
                return false;
            }
        }
        return true;
    }
}