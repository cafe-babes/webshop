package com.training360.cafebabeswebshop.report;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDao {


    private JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<OrderReport> ORDER_ROW_MAPPER = (rs, rowNum) -> new OrderReport(
            rs.getInt("year"),
            rs.getInt("month"),
            rs.getString("orderStatus"),
            rs.getInt("total"),
            rs.getInt("count")
    );

    public List<OrderReport> getMonthlyIncomeOfOrders() {
        return jdbcTemplate.query(
                "SELECT  YEAR(purchase_date) as year, month(purchase_date) as month, `order_status` as orderStatus, sum(`total`) as total, count(*) as count\n" +
                "FROM `orders`\n" +
                "GROUP BY YEAR(purchase_date), month(purchase_date), order_status  \n" +
                "ORDER BY `orders`.`order_status`, purchase_date  ASC", ORDER_ROW_MAPPER);
    }
}

