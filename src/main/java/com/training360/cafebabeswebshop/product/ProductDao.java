package com.training360.cafebabeswebshop.product;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = ((resultSet, i) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("code"),
            resultSet.getString("address"),
            resultSet.getString("name"),
            resultSet.getString("manufacture"),
            resultSet.getInt("price")

    ));

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Product getProduct(String address){

        try{
        return jdbcTemplate.queryForObject("select id, code, address, name, manufacture, price from products where address = ?",
                PRODUCT_ROW_MAPPER, address);
        } catch (EmptyResultDataAccessException e){
            throw new IllegalStateException();
        }
    }

    public List<Product> getProducts(){
        return jdbcTemplate.query("select id, code, address, name, manufacture, price from products", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Product(resultSet.getLong("id"), resultSet.getString("code"), resultSet.getString("address"),
                        resultSet.getString("name"), resultSet.getString("manufacture"), resultSet.getInt("price"));
            }
        });
    }
}
