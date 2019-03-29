package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;


    @PostMapping("/basket/{address}")
    public long saveBasketItemAndGetId(@PathVariable String address, @RequestBody BasketItem basketItem, Authentication authentication) {
        return basketService.saveBasketItemAndGetId(address, authentication, basketItem);
    }


    @GetMapping("/basket")
    public List<BasketItem> getBasketItems(Authentication authentication) {
        return authentication==null ? null : basketService.getBasketItems(authentication);
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
