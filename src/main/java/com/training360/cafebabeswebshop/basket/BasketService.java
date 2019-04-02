package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public long saveBasketItemAndGetId(String address, Authentication authentication, BasketItem basketItem) {
        basketItem.setUsername(authentication.getName());
        basketItem.setAddress(address);
        try {
            return basketDao.saveBasketItemAndGetId(basketItem);
        } catch (DataAccessException sql) {
            int piecesAlreadyInBasket = basketDao.getBasketItem(basketItem.getUsername(), basketItem.getAddress()).getPieces();
            basketItem.setPieces(basketItem.getPieces() + piecesAlreadyInBasket);
            basketDao.updateBasketItemPieces(basketItem);
            return 0;
        }
    }

    public void updateBasketItemPieces(Authentication authentication, BasketItem basketItem){
        basketItem.setUsername(authentication.getName());
        basketDao.updateBasketItemPieces(basketItem);
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
