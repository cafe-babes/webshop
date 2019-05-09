package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.order.Order;
import com.training360.cafebabeswebshop.order.OrderController;
import com.training360.cafebabeswebshop.order.OrderedProduct;
import com.training360.cafebabeswebshop.report.OrderReport;
import com.training360.cafebabeswebshop.report.ReportController;
import com.training360.cafebabeswebshop.report.ShippedProductReport;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class ReportTest {


        @Autowired
        ReportController reportController;


        @Test
        public void shippedProductReportTest(){
            int price = 97000;
            int count = 3;
            int total = price*count;
            ShippedProductReport spr = new ShippedProductReport(2019, 6, "Shark", price, count, total);

            assertEquals(2019, spr.getYear());
            assertEquals(6, spr.getMonth());
            assertEquals("Shark", spr.getProductname());
            assertEquals(97000, spr.getPrice());
            assertEquals(3, spr.getCount());
            assertEquals(291000, spr.getTotal());
        }

        @Test
        public void orderRiportTest(){
            OrderReport or = new OrderReport(2019, 2, "ACTIVE", 210000, 1);
            assertEquals(2019, or.getYear());
            assertEquals(2, or.getMonth());
            assertEquals("ACTIVE", or.getOrderStatus());
            assertEquals(210000, or.getTotal());
            assertEquals(1, or.getCount());
        }

        @Test
    public void contextLoads(){

            // When (examining the content)
            List<OrderReport> orderReports = reportController.getMonthlyIncomeOfOrders();
            List<ShippedProductReport> shippedProductReports = reportController.getShippedProducts();

            //Then (both lists have content)
            boolean orderReportIsNotEmpty = !orderReports.equals(Collections.emptyList());
            boolean shippedProductReportIsNotEmpty= !shippedProductReports.equals(Collections.emptyList());


        assertTrue(orderReportIsNotEmpty);
        assertTrue(shippedProductReportIsNotEmpty);
        }


    @Test
    public void testGetShippedProducts(){

            //Given (size of shipped products)
        int sizeOfShippedProducts= reportController.getShippedProducts().size();


        //When  (Summing the orders which are shipped)
        int shippedOrders = 0;
        for (OrderReport o: reportController.getMonthlyIncomeOfOrders()) {
            if(o.getOrderStatus().equals("SHIPPED")){
                shippedOrders += o.getCount();
            }
        }

        //Then (The values are equal)
        assertEquals(sizeOfShippedProducts, shippedOrders);
        }

}
