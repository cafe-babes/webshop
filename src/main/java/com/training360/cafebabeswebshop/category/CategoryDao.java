package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public long createCategoryAndGetId(Category category) throws DataAccessException {
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

    public Long getMinOrdinal(){
        return jdbcTemplate.queryForObject("SELECT MIN(ordinal) FROM category",
                (rs, rowNum) -> rs.getLong("MIN(ordinal)"));
    }

    public void reindexOrdinal(long ordinal) {
        jdbcTemplate.update("UPDATE category SET ordinal = ? WHERE ordinal = ?", ordinal+1, ordinal);
    }

    public void changeOrdinal(long id, long ordinal){
        jdbcTemplate.update("update category set ordinal = ? where id = ?;", ordinal, id);
    }

    public List<String> getCategoryNames(){
        return jdbcTemplate.query("select name from category", (resultSet, i) -> resultSet.getString("name"));
    }

    public void deleteCategory(long id) {
        jdbcTemplate.update("delete from category where id = ?", id);
    }

    public Category getCategory(String name) {
        return jdbcTemplate.queryForObject("select id, name, ordinal from category where name = ?", CATEGORY_ROW_MAPPER, name);
    }

    public void updateCategory(long id, Category category) {
        jdbcTemplate.update("update category set name = ?, ordinal = ? where id = ?",
                category.getName(),
                category.getOrdinal(),
                id);
    }
}
