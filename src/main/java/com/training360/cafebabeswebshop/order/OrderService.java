package com.training360.cafebabeswebshop.order;
import com.training360.cafebabeswebshop.basket.BasketDao;
import com.training360.cafebabeswebshop.basket.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BasketDao basketDao;


    public List<Order> listMyOrders(Authentication authentication){

        return orderDao.listMyOrders(authentication.getName());
    }

    public long saveOrderAndGetId(Authentication authentication, Order order){
        if (basketDao.getBasketItems(authentication.getName()).size() > 0) {
            long id = orderDao.saveOrderAndGetId(authentication.getName(), order);
            addOrderedProducts(authentication, order);
            basketDao.deleteBasket(authentication.getName());
            return id;
        } else {
            throw new IllegalStateException("The basket is empty");
        }
    }

    private void addOrderedProducts(Authentication authentication,Order order){
        for (BasketItem bi: basketDao.getBasketItems(authentication.getName())) {
            orderDao.saveOrderedProductAndGetId(
                    new OrderedProduct(bi.getProductId(),
                            saveOrderAndGetId(authentication,order),
                            bi.getPrice(), bi.getName()));
        }
    }
}
