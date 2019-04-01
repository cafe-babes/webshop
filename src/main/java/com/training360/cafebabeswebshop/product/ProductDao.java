package com.training360.cafebabeswebshop.product;

import com.training360.cafebabeswebshop.category.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            new Category(
                    resultSet.getLong("category_id"),
                    resultSet.getString("category.name"),
                    resultSet.getLong("category.ordinal"))
    ));

    private static final RowMapper<Product> PRODUCT_ROW_MAPPER2 = (resultSet, i) -> new Product(
            resultSet.getString("address"),
            resultSet.getString("name"),
            resultSet.getString("manufacture"),
            resultSet.getInt("price"));

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Product getProduct(String address) {
        try {
            return jdbcTemplate.queryForObject("select products.id, code, address, products.name, manufacture, price, product_status, category_id, category.name, category.ordinal " +
                            "FROM products LEFT JOIN category ON category_id=category.id " +
                            "WHERE address = ?",
                    PRODUCT_ROW_MAPPER, address);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException();
        }
    }

    public List<Product> getProducts() {
        return jdbcTemplate.query("select products.id, code, address, products.name, manufacture, price, product_status, category_id, category.name, category.ordinal " +
                        "FROM products LEFT JOIN category ON category_id=category.id " +
                        "WHERE product_status = 'ACTIVE' ORDER BY category.ordinal, products.name, manufacture",
                PRODUCT_ROW_MAPPER);
    }

    public List<Product> getProductsWithStartAndSize(int start, int size) {
        return jdbcTemplate.query("select products.id, code, address, products.name, manufacture, price, product_status, category_id, category.name, category.ordinal " +
                        "FROM products LEFT JOIN category ON category_id=category.id " +
                        "WHERE product_status = 'ACTIVE' ORDER BY category.ordinal, products.name, manufacture LIMIT ? OFFSET ?",
                PRODUCT_ROW_MAPPER,
                size,
                start
        );
    }
    public List<Product> getProductsWithStartAndSizeAndCategory(int start, int size, Category category) {
        return jdbcTemplate.query("select products.id, code, address, products.name, manufacture, price, product_status, category_id, category.name, category.ordinal " +
                        "FROM products LEFT JOIN category ON category_id=category.id " +
                        "WHERE product_status = 'ACTIVE' AND category.name = ? ORDER BY category.ordinal, products.name, manufacture LIMIT ? OFFSET ?",
                PRODUCT_ROW_MAPPER,
                category.getName(),
                size,
                start
        );
    }

    public long saveProductAndGetId(Product product) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into products (code, address, name,  manufacture, price, product_status, category_id) " +
                            "values (?,?,?,?,?,?,(SELECT id FROM category WHERE name=?))",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getAddress());
            ps.setString(3, product.getName());
            ps.setString(4, product.getManufacture());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, "ACTIVE");
            ps.setString(7, product.getCategory().getName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void updateProduct(long id, Product product) throws DataAccessException {
        jdbcTemplate.update("update products set code = ?, address = ?, name = ?, manufacture = ?, price = ?, " +
                        "category_id = (SELECT id FROM category WHERE name=?) where id = ?",
                product.getCode(),
                product.getAddress(),
                product.getName(),
                product.getManufacture(),
                product.getPrice(),
                product.getCategory().getName(),
                id);
    }

    public void deleteProduct(long id) {
        jdbcTemplate.update("update products set product_status = 'DELETED' where id = ?", id);
    }

    public Product getProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT products.id, code, address, products.name, manufacture, price, product_status, category_id, category.name, category.ordinal " +
                "FROM products  LEFT JOIN category ON category_id=category.id WHERE products.id = ? ", PRODUCT_ROW_MAPPER, id);
    }

    public List<Product> listAdviceProducts() {
        return jdbcTemplate.query("select products.name, products.manufacture, products.price, products.address from products " +
                "join ordered_products on products.id = ordered_products.product_id join orders on ordered_products.order_id = orders.id " +
                "where (orders.order_status = 'ACTIVE' " +
                "or orders.order_status = 'SHIPPED') order by orders.purchase_date desc limit 3", PRODUCT_ROW_MAPPER2);
    }
}
