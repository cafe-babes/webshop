package com.training360.cafebabeswebshop.order;
import com.training360.cafebabeswebshop.basket.BasketDao;
import com.training360.cafebabeswebshop.basket.BasketItem;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;
    private BasketDao basketDao;


    public List<Order> listMyOrders(Authentication authentication){

        return orderDao.listMyOrders(authentication.getName());
    }

    public long saveOrderAndGetId(Authentication authentication, Order order){
        long id = orderDao.saveOrderAndGetId(authentication.getName(), order);
        addOrderedProducts(authentication, order);
        basketDao.deleteBasket(authentication.getName());
        return id;
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
