package com.training360.cafebabeswebshop.order;
import com.training360.cafebabeswebshop.basket.BasketDao;
import com.training360.cafebabeswebshop.basket.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BasketDao basketDao;


    public Map<LocalDateTime, List<OrderedProduct>> listMyOrders(Authentication authentication){
        Map<LocalDateTime, List<OrderedProduct>> result = new HashMap<>();
        List<Order> orders = orderDao.listMyOrders(authentication.getName());

        for (Order o: orders) {
            result.put(o.getPurchaseDate(), listOrderedProductsByOrderId(o.getId()));
        }
        return result;
    }

    public List<Order> listAllOrders(){
        return orderDao.listAllOrders();
    }

    public List<OrderedProduct> listOrderedProductsByOrderId(long id){
        return orderDao.listOrderedProductsByOrderId(id);
    }

    public List<OrderedProduct> listAllOrderedProduct(){
        return orderDao.listAllOrderedProduct();
    }

    public long saveOrderAndGetId(Authentication authentication){
        int basketSize = basketDao.getBasketItems(authentication.getName()).size();
        Order o = new Order(0, orderDao.getUserId(authentication.getName()),
                countTotal(authentication), basketSize, "ACTIVE");
        if (basketSize > 0) {
            long id = orderDao.saveOrderAndGetId(authentication.getName(), o);
            o.setId(id);
            addOrderedProducts(authentication, o);
            basketDao.deleteBasket(authentication.getName());
            return id;
        } else {
            throw new IllegalStateException("The basket is empty");
        }
    }

    public void deleteOneItemFromOrder(long orderId, String address){
        orderDao.reduceOrderQuantityAndPriceWhenDeleting(orderId, address);
        orderDao.deleteOneItemFromOrder(orderId,address);
    }

    public void deleteOrder(long id){
        orderDao.deleteOrder(id);
    }

    public void updateOrderStatus(long id, String status){
        orderDao.updateOrderStatus(id, status);
    }

    private void addOrderedProducts(Authentication authentication,Order order){
        for (BasketItem bi: basketDao.getBasketItems(authentication.getName())) {
            orderDao.saveOrderedProductAndGetId(
                    new OrderedProduct(bi.getProductId(),
                            order.getId(), bi.getPrice(), bi.getName(), bi.getAddress()));
        }
    }

    private long countTotal(Authentication authentication){
        long sum = 0;
        for (BasketItem bi: basketDao.getBasketItems(authentication.getName())) {
            sum += bi.getPrice();
        }
        return sum;
    }
}
