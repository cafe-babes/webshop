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

    public boolean userCanBeSaved(User user){
        for (User u: userService.listUsers()) {
            if(u.getName().equals(user.getName())){
                return false;
            }
        }
        return true;
    }



}
