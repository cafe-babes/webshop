package com.training360.cafebabeswebshop.user;

import com.training360.cafebabeswebshop.product.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private  static final RowMapper<User> USER_ROW_MAPPER = ((rs, i) -> new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("user_status")));

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> listUsers() {
        return jdbcTemplate.query("select id, name, email, user_name, password, role, user_status from users", USER_ROW_MAPPER );
    }

    public void deleteUserById(long id) {
        jdbcTemplate.update("delete from users where id = ?", id);
    }

    public void updateUser(long id, User user) {
        jdbcTemplate.update("update ");
    }
}
