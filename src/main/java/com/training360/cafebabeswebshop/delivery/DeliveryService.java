package com.training360.cafebabeswebshop.delivery;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private DeliveryDao deliveryDao;

    public DeliveryService(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    public List<Delivery> getDeliveriesByUserId(Authentication authentication){
        return deliveryDao.getDeliveriesByUserId(authentication);
    }

    public Delivery getDeliveryById(long id){
        return deliveryDao.getDeliveryById(id);
    }
}
