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

    private static final RowMapper<ShippedProductReport> PRODUCT_ROW_MAPPER = (rs, rowNum) -> new ShippedProductReport(
            rs.getInt("year"),
            rs.getInt("month"),
            rs.getString("productname"),
            rs.getInt("price"),
            rs.getInt("total")
    );

    public List<OrderReport> getMonthlyIncomeOfOrders() {
        return jdbcTemplate.query(
                "SELECT  YEAR(purchase_date) as year, month(purchase_date) as month, `order_status` as orderStatus, sum(`total`) as total, count(*) as count\n" +
                "FROM `orders`\n" +
                "GROUP BY YEAR(purchase_date), month(purchase_date), order_status  \n" +
                "ORDER BY `orders`.`order_status`, purchase_date  ASC", ORDER_ROW_MAPPER);
    }

    public List<ShippedProductReport> getShippedProducts() {
        return jdbcTemplate.query("SELECT  YEAR(purchase_date) as year, month(purchase_date) as month, ordered_products.ordering_name as productname, ordered_products.ordering_price as price, orders.sum_quantity as count, orders.total as total \n" +
                "FROM orders\n" +
                "JOIN ordered_products on ordered_products.id = orders.id\n" +
                "WHERE orders.order_status = 'ACTIVE'", PRODUCT_ROW_MAPPER);
    }
}

