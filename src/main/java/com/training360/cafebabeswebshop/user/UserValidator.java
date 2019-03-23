package com.training360.cafebabeswebshop.user;

import java.util.List;

public class UserValidator {


    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean userCanBeSaved(User user) {
        return nameIsNotEmptyOrNull(user.getName()) && passwordIsNotEmptyOrNull(user.getPassword()) &&
                userIsNotRegisteredWithThisNameYet(user.getUserName());
    }

    public boolean userCanBeUpdated(User user) {
        return nameIsNotEmptyOrNull(user.getName()) && passwordIsNotEmptyOrNull(user.getPassword());
    }

    private boolean nameIsNotEmptyOrNull(String name) {
        return name != null && !name.trim().equals("");
    }

    private boolean passwordIsNotEmptyOrNull(String pass) {
        return pass != null && !pass.trim().equals("");
    }

    private boolean userIsNotRegisteredWithThisNameYet(String newUsername) {
        for (User reguser : userService.listUsers()) {
            if (newUsername.equals(reguser.getUserName())) {
                return false;
            }
        }
        return true;
    }
}