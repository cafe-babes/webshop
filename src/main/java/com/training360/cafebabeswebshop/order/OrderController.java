package com.training360.cafebabeswebshop.order;

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


    @GetMapping("/myorders")
    public List<Order> listMyOrders(Authentication authentication){
        return orderService.listMyOrders(authentication);
    }

    @PostMapping("/myorders")
    public long saveOrderAndGetId(Authentication authentication, @RequestBody Order order){
        return orderService.saveOrderAndGetId(authentication, order);
    }
}
