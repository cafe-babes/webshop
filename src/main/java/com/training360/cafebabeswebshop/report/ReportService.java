package com.training360.cafebabeswebshop.report;


import com.training360.cafebabeswebshop.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
//
//    public List<Order> getMonthlyIncomeOfOrders() {
//        return reportDao.getMonthlyIncomeOfOrders();
//    }
}
