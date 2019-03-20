package com.training360.cafebabeswebshop.basket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BasketDao {

    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Basket> BASKET_ROW_MAPPER = ((resultSet, i) -> new Basket());
}
