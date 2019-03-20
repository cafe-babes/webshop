package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

//    @PostMapping
//    public long saveBasketItemAndGetId(@RequestBody Basket basket) {
//        return basketService.saveBasketItemAndGetId(basket);
//    }

//    @GetMapping("/basket")
//    public List<Basket> getBasketItems(@PathVariable long userId) {
//        return basketService.getBasketItems(userId);
//    }
}
