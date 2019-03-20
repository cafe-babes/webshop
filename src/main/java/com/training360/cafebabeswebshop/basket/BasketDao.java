/*
package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BasketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Basket> BASKET_ROW_MAPPER = ((resultSet, i) -> new Basket(
            resultSet.getLong("id"),
            resultSet.getLong("user_id"),
            resultSet.getLong("product_id")
    ));

    public long saveBasketItemAndGetId(Basket basket) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO basket (user_id, product_id) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, basket.getUserId());
            ps.setLong(2, basket.getProductId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Basket> getBasketItems() {
        return jdbcTemplate.query("SELECT product.name, 1 FROM basket JOIN products ON basket.product_id=products.id",
                BASKET_ROW_MAPPER);
    }
}
*/
