package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserService;

public class OrderValidator {

    private UserService userService;

    public OrderValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean isValidOrder(Order order){
        return isValidUserId(order.getUserId()) &&
                isValidTotal(order.getTotal());
    }

    private boolean isValidUserId(long id){
        boolean presentUserId = false;

        for (User u: userService.listUsers()) {
            if (u.getId() == id){
                presentUserId = true;
            }
        }
        return (id > 0 && presentUserId);
    }

    private boolean isValidTotal(long total){
        return (total >= 0);
    }
}
