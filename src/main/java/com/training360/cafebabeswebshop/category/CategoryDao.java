package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, rowNum)->new Category(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("ordinal")
    );

    public List<Category> listCategories(){
        return jdbcTemplate.query("SELECT id, name, ordinal FROM category ORDER BY ordinal",
                CATEGORY_ROW_MAPPER);
    }

    public long createCategoryAndGetId(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO category (id, name, ordinal) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, category.getId());
            ps.setString(2, category.getName());
            ps.setLong(3, category.getOrdinal());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
