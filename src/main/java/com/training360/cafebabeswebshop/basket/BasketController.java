package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping
    public long saveBasketItemAndGetId(@RequestBody Basket basket) {
        return basketService.saveBasketItemAndGetId(basket);
    }
}
