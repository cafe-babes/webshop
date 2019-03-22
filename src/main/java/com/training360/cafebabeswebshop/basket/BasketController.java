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
    public long saveBasketItemAndGetId(@PathVariable String address, Authentication authentication) {
        return basketService.saveBasketItemAndGetId(address, authentication);
    }

    @GetMapping("/basket")
    public List<BasketItem> getBasketItems(Authentication authentication) {
        System.out.println(authentication);
        return basketService.getBasketItems(authentication);
    }

    @DeleteMapping("/basket")
    public void deleteBasket(Authentication authentication) {
        basketService.deleteBasket(authentication);
    }


    @DeleteMapping("/basket/{address}")
    public void deleteOneItem(Authentication authentication, @PathVariable String address){
        basketService.deleteOneItem(authentication, address);
    }
}
