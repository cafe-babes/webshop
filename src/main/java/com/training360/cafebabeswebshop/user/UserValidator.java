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

    public boolean deletionWasSuccessFul(long id) {
        int sizeOfUserListBeforeDeletion = userService.listUsers().size();
        userService.deleteUserById(id);
        int sizeOfUserListAfterDeletingAUser = userService.listUsers().size();
        return sizeOfUserListBeforeDeletion > sizeOfUserListAfterDeletingAUser;
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
        List<User> list = userService.listUsers();
        for (User reguser : list) {
            if (newUsername.equals(reguser.getUserName())) {
                return false;
            }
        }
        return true;
    }
}