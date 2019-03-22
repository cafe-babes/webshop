package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.basket.Basket;
import com.training360.cafebabeswebshop.basket.BasketDao;
import com.training360.cafebabeswebshop.basket.BasketProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;
    private BasketDao basketDao;


    public List<Order> listMyOrders(){
        return orderDao.listMyOrders();
    }

    public long saveOrderAndGetId(long id, Order order){
        basketDao.deleteBasket(id);
        return orderDao.saveOrderAndGetId(order);
    }

    private void addOrderedProduct(long user_id){
        for (BasketProduct bp: basketDao.getBasketItems(user_id)) {

        }
    }
}
