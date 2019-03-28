package com.training360.cafebabeswebshop.feedback;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDao {

    private static final RowMapper<Feedback> FEEDBACK_ROW_MAPPER = ((rs, i) -> new Feedback(
            rs.getInt("id"),
            rs.getTimestamp("feedback_date").toLocalDateTime(),
            rs.getString("feedback"),
            rs.getInt("rating"),
            rs.getInt("user_id"),
            rs.getInt("product_id")
    ));
    JdbcTemplate jdbcTemplate;


    public FeedbackDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Feedback> listFeedBacksByProductId(int productId) {
        return jdbcTemplate.query("select id, feedback, rating, user_id from feedback where product_id = ?",
                FEEDBACK_ROW_MAPPER, productId);
    }
}
