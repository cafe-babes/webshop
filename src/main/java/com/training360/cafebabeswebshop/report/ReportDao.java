package com.training360.cafebabeswebshop.report;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDao {


    private static final RowMapper<OrderReport> ORDER_ROW_MAPPER = (rs, rowNum) -> new OrderReport(
            rs.getInt("year(purchase_date)"),
            rs.getInt("month(purchase_date)"),
            rs.getString("order_status"),
            rs.getInt("total"),
            rs.getInt("sum_quantity")
    );
    private static final RowMapper<ShippedProductReport> PRODUCT_ROW_MAPPER = (rs, rowNum) -> new ShippedProductReport(
            rs.getInt("year"),
            rs.getInt("month"),
            rs.getString("productname"),
            rs.getInt("price"),
            rs.getInt("count"),
            rs.getInt("total")
    );
    private JdbcTemplate jdbcTemplate;

    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderReport> getMonthlyIncomeOfOrders() {
        return jdbcTemplate.query(
                "SELECT  year(purchase_date), month(purchase_date), order_status, sum(pieces*ordering_price) AS total, sum(pieces) AS sum_quantity\n" +
                        "FROM orders LEFT JOIN ordered_products ON orders.id = order_id \n" +
                        "GROUP BY YEAR(purchase_date), month(purchase_date), order_status  \n" +
                        "ORDER BY orders.order_status, purchase_date  ASC", ORDER_ROW_MAPPER);
    }

    public List<ShippedProductReport> getShippedProducts() {
        return jdbcTemplate.query("SELECT YEAR(purchase_date) as year, month(purchase_date) as month," +
                "ordered_products.ordering_name as productname, products.price as price, count(*) as count," +
                "sum(ordering_price) as total FROM orders JOIN ordered_products on ordered_products.order_id = orders.id JOIN products on products.id = ordered_products.product_id WHERE orders.order_status = 'SHIPPED' GROUP BY YEAr(purchase_date)," +
                "month(purchase_date), ordered_products.ordering_name, products.price", PRODUCT_ROW_MAPPER);
    }
}

