package com.training360.cafebabeswebshop.dashboard;

import com.training360.cafebabeswebshop.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardDao {
    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("user_name"),
            rs.getString("password"),
            rs.getInt("enabled"),
            rs.getString("role"),
            rs.getString("user_status")
    );


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
