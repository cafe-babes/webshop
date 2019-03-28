package com.training360.cafebabeswebshop.product;


import org.springframework.dao.DataAccessException;
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
            resultSet.getString("product_status"),
            resultSet.getString("category.name")
    ));

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Product getProduct(String address) {
        try {
            return jdbcTemplate.queryForObject("select products.id, code, address, products.name, manufacture, price, product_status, category.name " +
                            "FROM products JOIN category ON category_id=category.id where address = ?",
                    PRODUCT_ROW_MAPPER, address);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException();
        }
    }

    public List<Product> getProducts() {
        return jdbcTemplate.query("select products.id, code, address, products.name, manufacture, price, product_status, category.name " +
                        "FROM products JOIN category ON category_id=category.id WHERE product_status = 'ACTIVE' order by products.name, manufacture",
                PRODUCT_ROW_MAPPER);
    }

    public List<Product> getProductsWithStartAndSize(int start, int size) {
        return jdbcTemplate.query("select products.id, code, address, products.name, manufacture, price, product_status, category.name " +
                        "FROM products JOIN category ON category_id=category.id" +
                        "WHERE product_status = 'ACTIVE' order by products.name, manufacture LIMIT ? OFFSET ?",
                PRODUCT_ROW_MAPPER,
                size,
                start
        );
    }

    public long saveProductAndGetId(Product product) throws DataAccessException {
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

    public void updateProduct(long id, Product product) throws DataAccessException {
        jdbcTemplate.update("update products set `code` = ?, `address` = ?, `name` = ?, `manufacture` = ?, `price` = ? where id = ?",
                product.getCode(), product.getAddress(), product.getName(), product.getManufacture(), product.getPrice(), id);
    }

    public void deleteProduct(long id) {
        jdbcTemplate.update("update products set `product_status` = 'DELETED' where id = ?", id);
    }

    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("select products.id, code, address, products.name, manufacture, price, product_status, category.name " +
                        "FROM products JOIN category ON category_id=category.id WHERE id = ?",
                PRODUCT_ROW_MAPPER, id);
    }

    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject("SELECT `id`, `code`, `address`, `name`, `manufacture`, `price`, `product_status`, `category_id` FROM `products` WHERE id = ? ", PRODUCT_ROW_MAPPER, id);
    }


}
