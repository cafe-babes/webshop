package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public long saveBasketItemAndGetId(Basket basket) {
        return basketDao.saveBasketItemAndGetId(basket);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder(4).encode("admin"));
    }

//    public List<Basket> getBasketItems(long userId){
//        return basketDao.getBasketItems(userId);
//    }
}
