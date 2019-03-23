package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusE;
import com.training360.cafebabeswebshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    private OrderValidator validator;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
        this.validator = new OrderValidator(userService);
    }

    @PostMapping("/myorders")
    public ResultStatus saveOrderAndGetId(Authentication authentication, @RequestBody Order order){
        try{
            if (validator.isValidOrder(order)) {
                long id = orderService.saveOrderAndGetId(authentication, order);
                return new ResultStatus(ResultStatusE.OK, String.format("Order successfully created with id %d", id));
            } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid order");
            }
        } catch (IllegalStateException e){
            return new ResultStatus(ResultStatusE.NOT_OK, e.getMessage());
        }
    }

    @GetMapping("/myorders")
    public List<Order> listMyOrders(Authentication authentication){
        return orderService.listMyOrders(authentication);
    }

}
