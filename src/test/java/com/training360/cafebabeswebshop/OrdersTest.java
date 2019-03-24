package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.order.Order;
import com.training360.cafebabeswebshop.order.OrderController;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init_orders_table.sql")
public class OrdersTest {

    @Autowired
    private OrderController orderController;


    @Test
    public void contextLoads() {
        List<Order> orders = orderController.listAllOrders();

        assertEquals(orders.size(), 3);

        orderController.saveOrderAndGetId(null);

    }

    @Test
    public void deleteOrderById() {

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

}
