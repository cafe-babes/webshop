package com.training360.cafebabeswebshop.order;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Order> ORDER_ROW_MAPPER = ((rs, rowNum) -> new Order(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getLong("total"),
            rs.getLong("sum_quantity"),
            rs.getString("order_status")
    ));


    public long saveOrderAndGetId(Order order){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into orders (user_id, total, sum_quantity) values (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getUserId());
            ps.setLong(2, order.getTotal());
            ps.setLong(3, order.getSumQuantity());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Order> listMyOrders(){
        return jdbcTemplate.query(("select id, purchase_date, user_id, total, sum_quantity, order_status from orders"), ORDER_ROW_MAPPER);
    }

    public long saveOrderedProductAndGetId(OrderedProduct orderedProduct){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into ordered_products (product_id, order_id, ordering_price, ordering_name)" +
                            "values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderedProduct.getProductId());
            ps.setLong(2, orderedProduct.getOrderId());
            ps.setLong(3, orderedProduct.getOrderingPrice());
            ps.setString(4, orderedProduct.getOrderingName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
