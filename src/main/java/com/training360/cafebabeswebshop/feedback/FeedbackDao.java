package com.training360.cafebabeswebshop.feedback;

import com.training360.cafebabeswebshop.product.ProductDao;
import com.training360.cafebabeswebshop.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    private  final RowMapper<Feedback> FEEDBACK_ROW_MAPPER = ((rs, i) -> new Feedback(
            rs.getInt("id"),
            rs.getTimestamp("feedback_date").toLocalDateTime(),
            rs.getString("feedback"),
            rs.getInt("rating"),
            userDao.getUserById(rs.getLong("user_id")),
            productDao.getProductById(rs.getLong("product_id"))
    ));



    public FeedbackDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Feedback> listFeedBacksByProductId(long productId) {
        return jdbcTemplate.query("select id, feedback_date, feedback, rating, user_id, product_id \n" +
                        "from feedback \n" +
                        "where product_id = ? \n" +
                        "ORDER BY feedback_date DESC",
                FEEDBACK_ROW_MAPPER, productId);
    }

    public void giveAFeedback(Feedback feedback) {
        jdbcTemplate.update("INSERT INTO `feedback`(`feedback_date`, `feedback`, `rating`, `user_id`, `product_id`)"
                + "VALUES (?,?,?,?,?)", feedback.getFeedbackDate(), feedback.getFeedback(), feedback.getRating(), feedback.getUser().getId(), feedback.getProduct().getId());
    }
}
