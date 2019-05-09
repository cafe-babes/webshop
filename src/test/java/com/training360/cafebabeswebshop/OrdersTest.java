package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.delivery.Delivery;
import com.training360.cafebabeswebshop.order.*;
import com.training360.cafebabeswebshop.product.ResultStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class OrdersTest {

    @Autowired
    private OrderController orderController;
    @Autowired
    private BasketController basketController;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;

    @Test
    public void contextLoads() {
        List<Order> orders = orderController.listAllOrders();

        assertEquals(4, orders.size());

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


        assertEquals("DELETED", orderexample.getOrderStatus().name());

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

        assertEquals(false, allOrdersStatusIsActive);

    }

    @Test
    public void emptyBasketAfterOrder(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");
        TestingAuthenticationToken tat2 = new TestingAuthenticationToken("admin", "admin");
        Delivery delivery = new Delivery(1, "5432, Szeged, Dankó utca 4.", 1);
        //When
        orderController.saveOrderAndGetId(tat, delivery);
        orderController.saveOrderAndGetId(tat2, delivery);

        //Then
        assertEquals(basketController.getBasketItems(tat), Collections.emptyList());
        assertEquals(basketController.getBasketItems(tat2), Collections.emptyList());
    }

    /*@Test
    public void listOrderAfterCreateNewOrder(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        Delivery delivery = new Delivery(1, "Valami valami valami", 4);
        Order o = new Order(0, LocalDateTime.of(2019, 3, 12, 10, 23, 54), 4, 240000, 2, "ACTIVE", delivery);
        orderDao.saveOrderAndGetId(tat.getName(), o);
        List<Order> listMyOrders = orderController.listMyOrders(tat);



        //Then
        Order lastItem = listMyOrders.get(listMyOrders.size()-1);
        assertEquals(lastItem.getTotal(), 240000);
        assertEquals(lastItem.getPurchaseDate(), LocalDateTime.of(2019, 3, 12, 10, 23, 54));
        assertEquals(lastItem.getSumQuantity(), 2);
        assertEquals(lastItem.getOrderStatus(), "ACTIVE");
        assertEquals(lastItem.getDelivery().getDeliveryAddress(), "Valami valami valami");
    }*/


    @Test
    public void checkOrderDate(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        List<Order> orders = orderController.listAllOrders();
        LocalDateTime date1 = orders.get(0).getPurchaseDate();
        LocalDateTime date2 = orders.get(1).getPurchaseDate();

        //Then
        assertEquals(date1, LocalDateTime.of(2019, 4, 20, 22, 20, 20));
        assertEquals(date2, LocalDateTime.of(2019, 3, 20, 21, 20, 20));
    }

    @Test
    public void testUserId(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        List<Order> orders = orderController.listAllOrders();
        long user1 = orders.get(0).getUserId();

        //Then
        assertEquals(2L, user1);
    }

    @Test
    public void testOrderItemsQuantity(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        List<Order> orders = orderController.listAllOrders();
        long quantity = orders.get(0).getSumQuantity();

        //Then
        assertEquals(3L, quantity);
    }

    @Test
    public void testValue(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        List<Order> orders = orderController.listAllOrders();
        long value = orders.get(0).getTotal();

        //Then
        assertEquals(96600L, value);
    }

    @Test
    public void testStatus(){
        //Given
        TestingAuthenticationToken tat = new TestingAuthenticationToken("user", "user");

        //When
        List<Order> orders = orderController.listAllOrders();
        String status1 = orders.get(0).getOrderStatus().toString();
        String status2 = orders.get(1).getOrderStatus().toString();
        String status3 = orders.get(2).getOrderStatus().toString();

        //Then
        assertEquals("SHIPPED", status1);
        assertEquals("ACTIVE", status2);
        assertEquals("SHIPPED", status3);
    }

    @Test
    public void testDeleteOneItemFromOrder(){
        //When
        ResultStatus rs = orderController.deleteOneItemFromOrder(1, "blowfish");
        ResultStatus rs2 = orderController.deleteOneItemFromOrder(1, "jb");
        ResultStatus rs3 = orderController.deleteOneItemFromOrder(1, "jb-1");
        ResultStatus rs4 = orderController.deleteOneItemFromOrder(1, "surf_coder");

        //Then
        assertEquals("Ordered product deleted successfully", rs.getMessage());
        assertEquals("Invalid id, or address", rs2.getMessage());
        assertEquals("Ordered product deleted successfully", rs3.getMessage());
        assertEquals("Ordered product deleted successfully", rs4.getMessage());
    }

    @Test
    public void changeOrderStatusToShipped(){
        TestingAuthenticationToken tat = new TestingAuthenticationToken("admin", "admin");
        Delivery delivery = new Delivery(1, "5432, Szeged, Dankó utca 4.", 1);

        long orderId = orderService.saveOrderAndGetId(tat, delivery);

        assertEquals(OrderStatus.ACTIVE, orderDao.findOrderById(orderId).getOrderStatus());

        orderService.updateOrderStatus(orderId, "shipped");

        assertEquals(OrderStatus.SHIPPED, orderDao.findOrderById(orderId).getOrderStatus());
    }

    @Test
    public void changeOrderStatusToShippedWithWrongAddress(){
        TestingAuthenticationToken tat = new TestingAuthenticationToken("admin", "admin");
        Delivery delivery = new Delivery(1, "5432, Szeged, Dankó utca 4.", 1);

        long orderId = orderService.saveOrderAndGetId(tat, delivery);

        assertEquals(OrderStatus.ACTIVE, orderDao.findOrderById(orderId).getOrderStatus());

        ResultStatus rs = orderController.updateOrderStatus(orderId, "shippe");

        assertEquals("Invalid id or status", rs.getMessage());
    }



}
