package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
}
