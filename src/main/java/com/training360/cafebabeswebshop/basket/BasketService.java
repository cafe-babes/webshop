package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public long saveBasketItemAndGetId(String address, Basket basket) {
        return basketDao.saveBasketItemAndGetId(address, basket);
    }

    public List<BasketProduct> getBasketItems(long userId){
        return basketDao.getBasketItems(userId);
    }

    public void deleteBasket(long userId) {
        basketDao.deleteBasket(userId);
    }
}
