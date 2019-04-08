package com.training360.cafebabeswebshop.dashboard;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardDao {
    private JdbcTemplate jdbcTemplate;

    public DashboardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countAllUsers() {
        return jdbcTemplate.queryForObject("select count(id) from users", Integer.class);
    }

    public int countAllProducts(){
        return jdbcTemplate.queryForObject("select count(id) from products", Integer.class);
    }

    public int countAllActiveProducts(){
        return jdbcTemplate.queryForObject("select count(id) from products where product_status = 'ACTIVE'", Integer.class);
    }

    public int countAllOrders(){
        return jdbcTemplate.queryForObject("select count(id) from orders", Integer.class);
    }

    public int countAllActiveOrders(){
        return jdbcTemplate.queryForObject("select count(id) from orders where order_status='ACTIVE'", Integer.class);
    }
}
