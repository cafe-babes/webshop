package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public long saveBasketItemAndGetId(Basket basket) {
        return basketDao.saveBasketItemAndGetId(basket);
    }

    public List<Basket> getBasketItems(){
        return basketDao.getBasketItems();
    }
}
