package com.training360.cafebabeswebshop.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    private static final RowMapper<BasketItem> BASKETITEM_ROW_MAPPER = ((resultSet, i) -> new BasketItem(
            resultSet.getLong("product_id"),
            resultSet.getString("products.name"),
            resultSet.getString("products.address"),
            resultSet.getInt("products.price"),
            resultSet.getInt("pieces")
    ));

    public long saveBasketItemAndGetId(BasketItem basketItem) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO basket(user_id, product_id, pieces) VALUES ((SELECT id FROM users WHERE user_name = ?), (SELECT id FROM products WHERE address = ?), ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, basketItem.getUsername());
            ps.setString(2, basketItem.getAddress());
            ps.setInt(3, basketItem.getPieces());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<BasketItem> getBasketItems(String userName) {
        return jdbcTemplate.query(
                "SELECT product_id, products.name, products.address, products.price, pieces FROM basket \n" +
                        "JOIN products ON basket.product_id=products.id \n" +
                        "JOIN users ON basket.user_id=users.id \n" +
                        "WHERE user_name = ?",
                BASKETITEM_ROW_MAPPER,
                userName);
    }

    public BasketItem getBasketItem(String userName, String address) {
        return jdbcTemplate.queryForObject(
                "SELECT product_id, products.name, products.address, products.price, pieces FROM basket \n" +
                        "JOIN products ON basket.product_id=products.id \n" +
                        "JOIN users ON basket.user_id=users.id \n" +
                        "WHERE users.user_name = ? and products.address = ?",
                BASKETITEM_ROW_MAPPER,
                userName, address);
    }

    public void updateBasketItemPieces(BasketItem basketItem) {
        jdbcTemplate.update("update basket set pieces = ? where product_id = (SELECT id FROM products WHERE address = ?) AND user_id = (SELECT id FROM users WHERE user_name = ?)",
                basketItem.getPieces(), basketItem.getAddress(), basketItem.getUsername());
    }

    public void deleteBasket(String userName) {
        jdbcTemplate.update("DELETE FROM basket WHERE user_id =(select id from users where users.user_name = ?)", userName);
    }

    public void deleteOneItem(String userName, String address) {
        jdbcTemplate.update("delete from basket where user_id=(select id from users where users.user_name = ?) and product_id=(select id from products where products.address=?)", userName, address);
    }
}

