package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.order.*;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class OrdersTest {

    @Autowired
    private OrderController orderController;
    @Autowired
    private BasketController basketController;
    @Test
    public void contextLoads() {
        List<Order> orders = orderController.listAllOrders();

        assertEquals(orders.size(), 5);

    }


    @Test
    public void testDeleteOrderById() {

        // Given (a list of orders given)
        List<Order> orders = orderController.listAllOrders();


        long idexample = orders.get(0).getId();
        // When (logically deleting an order)
        orderController.deleteOrder(idexample);

        //Then (deleted order's status is deleted)
        orders = orderController.listAllOrders();
        Order orderexample = orders.stream().filter((o) -> o.getId() == idexample).findFirst().get();


        assertEquals(orderexample.getOrderStatus().name(), "DELETED");

    }


    @Test
    public void testStatusOfOrderIsActiveDefault() {

        // Given (a list of orders given)
        List<Order> orders = orderController.listAllOrders();

        boolean allOrdersStatusIsActive = true;

        for (Order o : orders) {
            if(!o.getOrderStatus().name().equals("ACTIVE")){
                allOrdersStatusIsActive = false;
            }
        }

        assertEquals(allOrdersStatusIsActive, false);

    }

    @Test
    public void emptyBasketAfterOrder(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");
        TestingAuthenticationToken tat2 = new TestingAuthenticationToken("admin", "admin");

        //When
        orderController.saveOrderAndGetId(tat);
        orderController.saveOrderAndGetId(tat2);

        //Then
        assertEquals(basketController.getBasketItems(tat), Collections.emptyList());
        assertEquals(basketController.getBasketItems(tat2), Collections.emptyList());
    }

    @Test
    public void orderContainsDate(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        Map<LocalDateTime, List<OrderedProduct>> listMyOrders = orderController.listMyOrders(tat);
        List<LocalDateTime> keys = new ArrayList<>(listMyOrders.keySet());

        for(Map.Entry<LocalDateTime, List<OrderedProduct>> entry: listMyOrders.entrySet()){
            System.out.println(entry.getKey());
        }
        LocalDateTime date2 = keys.get(0);
        LocalDateTime date1 = keys.get(3);


        //Then
        assertEquals(date1, LocalDateTime.of(2019, 01, 20, 21, 20, 20));
        assertEquals(date2, LocalDateTime.of(2019, 04, 20, 22, 20, 20));
    }

    @Test
    public void orderContainsProduct(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");
        Map<LocalDateTime, List<OrderedProduct>> listMyOrders = orderController.listMyOrders(tat);
        List<LocalDateTime> keys = new ArrayList<>(listMyOrders.keySet());
        List<OrderedProduct> values = listMyOrders.get(keys.get(1));
        for(int i = 0; i < values.size(); i++){
            System.out.println(values.get(i).getOrderingName());
        }

        //When
        String name1 = values.get(0).getOrderingName();

        //Given
        assertEquals(name1, "Blow Fish");
    }

    @Test
    public void ordeDateInOrder(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        Map<LocalDateTime, List<OrderedProduct>> listMyOrders = orderController.listMyOrders(tat);
        List<LocalDateTime> keys = new ArrayList<>(listMyOrders.keySet());
        LocalDateTime date0 = keys.get(0);
        LocalDateTime date1 = keys.get(1);
        LocalDateTime date2 = keys.get(2);
        LocalDateTime date3 = keys.get(3);

        //Then
        assertEquals(date0, LocalDateTime.of(2019, 04, 20, 22, 20, 20));
        assertEquals(date1, LocalDateTime.of(2019, 03, 20, 21, 20, 20));
        assertEquals(date2, LocalDateTime.of(2019, 02, 20, 21, 20, 20));
        assertEquals(date3, LocalDateTime.of(2019, 01, 20, 21, 20, 20));

    }

    }

