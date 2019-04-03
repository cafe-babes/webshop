package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.delivery.Delivery;
import com.training360.cafebabeswebshop.delivery.DeliveryController;
import com.training360.cafebabeswebshop.delivery.DeliveryDao;
import com.training360.cafebabeswebshop.delivery.DeliveryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")

public class DeliveryTest {

    @Autowired
    DeliveryDao deliveryDao;
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    DeliveryController deliveryController;

    @Test
    public void deliveryCreateTest(){

        Delivery delivery = new Delivery(0, "Szeged Szeva u. 11.", 4);

        assertEquals(delivery.getDeliveryAddress(), "Szeged Szeva u. 11.");
        assertEquals(delivery.getUserId(), 4);

    }

    @Test
    public void setDeliveryCreateTest(){

        Delivery delivery = new Delivery(0, "Szeged Szeva u. 11.", 4);

        delivery.setDeliveryId(1);
        assertEquals(delivery.getDeliveryId(), 1);

        delivery.setDeliveryAddress("Szolnok Szeva u. 12");
        assertEquals(delivery.getDeliveryAddress(), "Szolnok Szeva u. 12");

        delivery.setUserId(5);
        assertEquals(delivery.getUserId(), 5);

    }



    @Test
    public void listDeliveriesTest(){

        List<Delivery> deliveries = deliveryDao.getDeliveries();

        assertEquals(deliveries.size(), 1);
    }

    @Test
    public void addNewDeliveryTest(){

        Delivery delivery = new Delivery(0, "Szeged Szeva u. 11.", 4);


        long id = deliveryDao.saveDeliveryAndGetId("user", delivery);

        List<Delivery> deliveries = deliveryDao.getDeliveries();

        assertEquals(deliveries.get(deliveries.size()-1).getDeliveryId(), id);
        assertEquals(deliveries.get(deliveries.size()-1).getDeliveryAddress(), "Szeged Szeva u. 11.");

    }

    @Test
    public void getDeliveryByIdControllerTest(){

        Delivery delivery = new Delivery(0, "Szeged Szeva u. 11.", 4);

        long id = deliveryDao.saveDeliveryAndGetId("user", delivery);

        Delivery result = deliveryController.getDeliveryById(id);

        assertEquals(result.getDeliveryAddress(), "Szeged Szeva u. 11.");
    }

    @Test

    public void getDeliveriesByUserIdControllerTest(){

        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        Delivery delivery = new Delivery(0, "Szeged Szeva u. 11.", 4);

        deliveryDao.saveDeliveryAndGetId("user", delivery);

        List<Delivery> deliveries = deliveryController.getDeliveriesByUserId(tat);

        assertEquals(deliveries.size(), 2);
    }
}
