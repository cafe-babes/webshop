package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.dashboard.DashboardController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init_dashboard.sql")
public class DashBoardTest {

    @Autowired
    private DashboardController dashboardController;

    @Test
    public void getList(){
        int users = dashboardController.listOfResults().get(0);
        int allProducts = dashboardController.listOfResults().get(1);
        int allActiveProducts = dashboardController.listOfResults().get(2);
        int allOrders = dashboardController.listOfResults().get(3);
        int allActiveOrders = dashboardController.listOfResults().get(4);

        assertEquals(3, users);
        assertEquals(6, allProducts);
        assertEquals(7, allOrders);
        assertEquals(4, allActiveProducts);
        assertEquals(5, allActiveOrders);
    }
}
