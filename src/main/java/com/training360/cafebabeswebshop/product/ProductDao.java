package com.training360.cafebabeswebshop.product;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
            resultSet.getInt("price"),
            resultSet.getString("product_status")
    ));

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Product getProduct(String address){
        try{
        return jdbcTemplate.queryForObject("select id, code, address, name, manufacture, price, product_status from products where address = ?",
                PRODUCT_ROW_MAPPER, address);
        } catch (EmptyResultDataAccessException e){
            throw new IllegalStateException();
        }
    }


    public List<Product> getProducts(){
        return jdbcTemplate.query("select id, code, address, name, manufacture, price, product_status from products order by name, manufacture",
                PRODUCT_ROW_MAPPER);
    }

    public List<Product> getActiveProducts(){
        return jdbcTemplate.query("select id, code, address, name, manufacture, price, product_status from products " +
                        "WHERE product_status = 'ACTIVE' order by name, manufacture",
                PRODUCT_ROW_MAPPER);
    }

    public List<Product> getProductsWithStartAndSize(int start, int size){
        return jdbcTemplate.query("select id, code, address, name, manufacture, price, product_status from products " +
                        "WHERE product_status = 'ACTIVE' order by name, manufacture LIMIT ? OFFSET ?",
                PRODUCT_ROW_MAPPER,
                size,
                start
        );
    }


    public long saveProductAndGetId(Product product){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into products (`code`, `address`, `name`,  `manufacture`, `price`, `product_status`) values (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getAddress());
            ps.setString(3, product.getName());
            ps.setString(4, product.getManufacture());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, "ACTIVE");
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void updateProduct(long id, Product product){
            jdbcTemplate.update("update products set `code` = ?, `address` = ?, `name` = ?, `manufacture` = ?, `price` = ? where id = ?",
                    product.getCode(), product.getAddress(), product.getName(), product.getManufacture(), product.getPrice(), id);

    }

    public void deleteProduct(long id){
        jdbcTemplate.update("update products set `product_status` = 'DELETED' where id = ?", id);
    }

    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("select id, code, address, name, manufacture, price, product_status from products where id = ?",
                PRODUCT_ROW_MAPPER, id);
    }


}
