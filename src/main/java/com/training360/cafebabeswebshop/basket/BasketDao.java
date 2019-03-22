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

    public BasketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Basket> BASKET_ROW_MAPPER = ((resultSet, i) -> new Basket(
            resultSet.getLong("id"),
            resultSet.getLong("user_id"),
            resultSet.getLong("product_id")
    ));

    private static final RowMapper<BasketItem> BASKETPRODUCT_ROW_MAPPER = ((resultSet, i) -> new BasketItem(
            resultSet.getLong("product_id"),
            resultSet.getString("products.name"),
            resultSet.getInt("products.price"),
            resultSet.getInt("amount"),
            resultSet.getString("products.address")
    ));

    public long saveBasketItemAndGetId(String address, Basket basket) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO basket(user_id, product_id) VALUES ( ? , (SELECT id FROM products WHERE address = ?))",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, basket.getUserId());
            ps.setString(2, address);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<BasketItem> getBasketItems(String userName) {
        return jdbcTemplate.query(
                "SELECT product_id, products.name, products.address, products.price, 1 as 'amount' FROM basket \n" +
                        "JOIN products ON basket.product_id=products.id \n" +
                        "JOIN users ON basket.user_id=users.id \n" +
                        "WHERE user_name = ?",
                BASKETPRODUCT_ROW_MAPPER,
                userName);
    }

    public void deleteBasket(String userName) {
        jdbcTemplate.update("DELETE FROM basket WHERE user_name = ?", userName);
    }

    public void deleteOneItem(String userName, String address) {
        jdbcTemplate.update("delete from basket where user_name = ? and product_id = ?", userName, address);
    }
}
