package com.training360.cafebabeswebshop.user;

public class UserValidator {


    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

//    public boolean userCanBeSaved(User user){
//        for (User u: userService.listUsers()) {
//            if(u.getEmail().equals(user.getEmail())){
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean userCanBeSaved(User user) {
        for (User u : userService.listUsers()) {
            if (user.getName() == null ||
                    user.getName().trim().equals("") ||
                    u.getUserName().equals(user.getUserName()) ||
                    user.getPassword() == null ||
                    user.getPassword().trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean userCanBeUpdated(User user) {
        if (user.getName() == null ||
                user.getName().trim().equals("") ||
                user.getPassword() == null ||
                user.getPassword().trim().equals("")) {
            return false;
        }
        for (User u : userService.listUsers()) {
            if (u.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        return true;
    }


}
