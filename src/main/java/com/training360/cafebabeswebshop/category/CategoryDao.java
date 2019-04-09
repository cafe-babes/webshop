package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;

@Repository
public class CategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, rowNum) -> new Category(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("ordinal")
    );

    public List<Category> listCategories() {
        return jdbcTemplate.query("SELECT id, name, ordinal FROM category ORDER BY ordinal",
                CATEGORY_ROW_MAPPER);
    }

    public Category getCategoryByName(String categoryName) {
        return jdbcTemplate.queryForObject("SELECT id, name, ordinal FROM category WHERE name = ? LIMIT 1",
                CATEGORY_ROW_MAPPER, categoryName);
    }

    public long createCategoryAndGetId(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO category (name, ordinal) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setLong(2, category.getOrdinal());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Long getMaxOrdinal() {
        return jdbcTemplate.queryForObject("SELECT MAX(ordinal) FROM category",
                (rs, rowNum) -> rs.getLong("MAX(ordinal)"));
    }

    public Long getMinOrdinal() {
        return jdbcTemplate.queryForObject("SELECT MIN(ordinal) FROM category",
                (rs, rowNum) -> rs.getLong("MIN(ordinal)"));
    }

    public void increaseOrdinal(long ordinal) {
        jdbcTemplate.update("UPDATE category SET ordinal = ordinal +1 WHERE ordinal >= ?", ordinal);
    }

    public void decreaseOrdinal(long ordinal) {
        jdbcTemplate.update("UPDATE category SET ordinal = ordinal -1 WHERE ordinal <= ?", ordinal);
    }

    public List<String> getCategoryNames() {
        return jdbcTemplate.query("select name from category", (resultSet, i) -> resultSet.getString("name"));
    }

    public int deleteCategory(long id) {
        return jdbcTemplate.update("delete from category where id = ?", id);
    }

    public Category getCategory(String name) {
        return jdbcTemplate.queryForObject("select id, name, ordinal from category where name = ?", CATEGORY_ROW_MAPPER, name);
    }

    public int updateCategory(long id, Category category){
        return jdbcTemplate.update("update category set name = ?, ordinal = ? where id = ?",
                category.getName(),
                category.getOrdinal(),
                id);
    }
}
