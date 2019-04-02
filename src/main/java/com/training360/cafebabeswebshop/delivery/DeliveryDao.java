package com.training360.cafebabeswebshop.delivery;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;


@Repository
public class DeliveryDao {

    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Delivery> DELIVERY_ROW_MAPPER = (rs, rowNum) -> new Delivery(
            rs.getLong("id"),
            rs.getString("address"),
            rs.getLong("user_id")
    );

    public DeliveryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long saveDeliveryAndGetId(String userName, Delivery delivery){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into delivery (address, user_id) " +
                            "values (?,(SELECT id FROM users WHERE user_name = ?))",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, delivery.getDeliveryAddress());
            ps.setString(2, userName);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Delivery getDeliveryById(long id){
        return jdbcTemplate.queryForObject("select id, address, user_id from delivery where id = ?", DELIVERY_ROW_MAPPER, id);
    }

    public List<Delivery> getDeliveries(){
        return jdbcTemplate.query("select id, address, user_id from delivery", DELIVERY_ROW_MAPPER);
    }

    public List<Delivery> getDeliveriesByUserId(Authentication authentication){
        return jdbcTemplate.query("select delivery.id as id, address, user_id from delivery LEFT JOIN users ON delivery.user_id=users.id where users.user_name = ? OR user_id IS NULL",
                DELIVERY_ROW_MAPPER, authentication.getName());
    }
}
