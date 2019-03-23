package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public long saveBasketItemAndGetId(String address, Authentication authentication) {
        for (BasketItem item : basketDao.getBasketItems(authentication.getName())) {
            if (item.getAddress().equals(address))
                return -1;
        }
        return basketDao.saveBasketItemAndGetId(address, authentication.getName());
    }

    public List<BasketItem> getBasketItems(Authentication authentication){
        return basketDao.getBasketItems(authentication.getName());
    }

    public void deleteBasket(Authentication authentication) {
        basketDao.deleteBasket(authentication.getName());
    }

    public void deleteOneItem(Authentication authentication, String address) {
        basketDao.deleteOneItem(authentication.getName(), address);
    }
}
