package com.training360.cafebabeswebshop.report;


import com.training360.cafebabeswebshop.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


//    @GetMapping("/reports/orders")
//    public List<Order> getMonthlyIncomeOfOrders(){
//        return reportService.getMonthlyIncomeOfOrders();
//    }

}
