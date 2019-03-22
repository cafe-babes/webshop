package com.training360.cafebabeswebshop.user;

import com.training360.cafebabeswebshop.product.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER = ((rs, i) -> new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getInt("enabled"),
                    rs.getString("role"),
                    rs.getString("user_status")));

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> listUsers() {
        return jdbcTemplate.query("SELECT `id`, `name`, `email`, `user_name`, `password`, `enabled`, `role`, `user_status` FROM `users`", USER_ROW_MAPPER );
    }

    public void deleteUserById(long id) {
        jdbcTemplate.update("delete from users where id = ?", id);
    }

    public void updateUser(long id, User user) {
        jdbcTemplate.update("update users set name = ?, email = ?, user_name = ?, password = ?, enabled = ?, role = ?, user_status = ? where id = ?",
                user.getName(), user.getEmail(), user.getUserName(), user.getPassword(), user.getEnabled(), user.getRole(), user.getUserStatus(), id);
    }

    public long insertUserAndGetId(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO users(name, enabled, user_name, password) VALUES ( ?, ?, ?, ?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getName());
                    ps.setInt(2, 1);
                    ps.setString(3, user.getUserName());
                    ps.setString(4, user.getPassword());
                    return ps;
                }, keyHolder
        );

        return keyHolder.getKey().longValue();
    }
}
