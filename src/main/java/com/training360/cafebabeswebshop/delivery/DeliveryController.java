package com.training360.cafebabeswebshop.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/delivery")
    public List<Delivery> getDeliveriesByUserId(Authentication authentication){
        return deliveryService.getDeliveriesByUserId(authentication);
    }

    @GetMapping("/delivery/{id}")
    public Delivery getDeliveryById(@PathVariable long id){
        return deliveryService.getDeliveryById(id);
    }
}
