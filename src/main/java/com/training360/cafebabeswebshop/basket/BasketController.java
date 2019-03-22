package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    private int user_id_for_test = 1;

    @PostMapping("/basket/{address}")
    public long saveBasketItemAndGetId(@PathVariable String address, @RequestBody Basket basket) {
        return basketService.saveBasketItemAndGetId(address, basket);
    }

    @GetMapping("/basket")
    public List<BasketItem> getBasketItems(Authentication authentication) {
        System.out.println(authentication.getName());
        return basketService.getBasketItems(authentication.getName());
    }

    @DeleteMapping("/basket")
    public void deleteBasket(Authentication authentication) {
        basketService.deleteBasket(authentication.getName());
    }
}
