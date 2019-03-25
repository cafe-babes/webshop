package com.training360.cafebabeswebshop.report;

import com.training360.cafebabeswebshop.order.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDao {


    private JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Order> getMonthlyIncomeOfOrders() {
//        return jdbcTemplate.query("");
//    }
}
