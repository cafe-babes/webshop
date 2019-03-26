package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.order.Order;
import com.training360.cafebabeswebshop.order.OrderController;
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
    public void testGetMonthlyIncomeOfOrders(){
        List<OrderReport> orderReports = reportController.getMonthlyIncomeOfOrders();
        assertTrue(!orderReports.equals(Collections.emptyList()));
        }


    @Test
    public void testgetShippedProducts(){
        List<ShippedProductReport> shippedProductReports = reportController.getShippedProducts();
        assertTrue(!shippedProductReports.equals(Collections.emptyList()));
    }

}
