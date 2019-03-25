package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, rowNum) -> new Order(
            rs.getLong("id"),
            rs.getTimestamp("purchase_date").toLocalDateTime(),
            rs.getLong("user_id"),
            rs.getLong("total"),
            rs.getLong("sum_quantity"),
            rs.getString("order_status")
    );
    private static final RowMapper<OrderedProduct> ORDERED_PRODUCT_ROW_MAPPER = (rs, rowNum) -> new OrderedProduct(
            rs.getLong("id"),
            rs.getLong("product_id"),
            rs.getLong("order_id"),
            rs.getLong("ordering_price"),
            rs.getString("ordering_name")
    );

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

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long saveOrderAndGetId(String userName, Order order){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into orders (purchase_date, user_id, total, sum_quantity) " +
                            "values (?,(SELECT id FROM users WHERE user_name = ?),?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(order.getPurchaseDate()));
            ps.setString(2, userName);
            ps.setLong(3, order.getTotal());
            ps.setLong(4, order.getSumQuantity());
           // ps.setString(5, String.valueOf(order.getOrderStatus()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public long getUserId(String userName){
       User u = jdbcTemplate.queryForObject("select id, name, email, user_name, password, enabled, role, user_status " +
               "from users where user_name = ?", USER_ROW_MAPPER, userName);
       return u.getId();
    }

    public Order findOrderById(long id){
        return jdbcTemplate.queryForObject("select id, purchase_date, user_id, total, sum_quantity, order_status from orders where id = ?",
                ORDER_ROW_MAPPER, id);
    }

    public List<Order> listMyOrders(String username){
        return jdbcTemplate.query(("select orders.id, purchase_date, user_id, total, sum_quantity, order_status " +
                "from orders join users on users.id = orders.user_id " +
                "where users.user_name = ? order by purchase_date desc"), ORDER_ROW_MAPPER, username);
    }

    public long saveOrderedProductAndGetId(OrderedProduct orderedProduct){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into ordered_products " +
                            "(product_id, order_id, ordering_price, ordering_name) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderedProduct.getProductId());
            ps.setLong(2, orderedProduct.getOrderId());
            ps.setLong(3, orderedProduct.getOrderingPrice());
            ps.setString(4, orderedProduct.getOrderingName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Order> listAllOrders(){
        return jdbcTemplate.query("select id, purchase_date, user_id, total, sum_quantity, order_status from orders order by purchase_date desc", ORDER_ROW_MAPPER);
    }

    public List<OrderedProduct> listOrderedProductsByOrderId(long id){
        return jdbcTemplate.query("select id, product_id, order_id, ordering_price, ordering_name from ordered_products where order_id =?",
                ORDERED_PRODUCT_ROW_MAPPER, id);
    }

    public List<OrderedProduct> listAllOrderedProduct(){
        return jdbcTemplate.query("select id, product_id, order_id, ordering_price, ordering_name from ordered_products", ORDERED_PRODUCT_ROW_MAPPER);
    }

    public void deleteOneItemFromOrder(long orderId, String address){
        jdbcTemplate.update("delete ordered_products from ordered_products inner join products on product_id = products.id " +
                "where products.address = ? AND ordered_products.order_id = ?", address, orderId);
    }

    public OrderedProduct findOrderedProductByProductAddress(String address){
        System.out.println(address);
        return jdbcTemplate.queryForObject("select ordered_products.id, product_id, order_id, ordering_price, ordering_name from ordered_products join products on " +
                "product_id = products.id where products.address = ?", ORDERED_PRODUCT_ROW_MAPPER, address);
    }

    public void reduceOrderQuantityAndPriceWhenDeleting(long orderId, String address){
        Order o = findOrderById(orderId);
        OrderedProduct op = findOrderedProductByProductAddress(address);
        long newSumQuantity = o.getSumQuantity() - 1;
        long newTotal = o.getTotal() - op.getOrderingPrice();
        jdbcTemplate.update("update orders set sum_quantity = ?", newSumQuantity);
        jdbcTemplate.update("update orders set total = ?", newTotal);
    }

    public void deleteOrder(long id){
        jdbcTemplate.update("update orders set order_status = 'DELETED' where id = ?", id);
    }

    public void updateOrderStatus(long id, String status){
        jdbcTemplate.update("update orders set order_status = ? where id = ?", status, id);
    }

}
