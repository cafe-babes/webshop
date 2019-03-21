package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.training360.cafebabeswebshop.product.*;

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

    @GetMapping("/basket/{userId}")
    public List<BasketProduct> getBasketItems(@PathVariable long userId) {
        return basketService.getBasketItems(userId);
    }

    @DeleteMapping("/basket/{userId}")
    public void deleteBasket(@PathVariable long userId) {
        basketService.deleteBasket(userId);
    }
}
