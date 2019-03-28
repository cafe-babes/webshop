package com.training360.cafebabeswebshop.feedback;

import java.time.LocalDateTime;

public class Feedback {

    private int id;
    private LocalDateTime feedbackDate;
    private String feedback;
    private int rating;
    private int userId;
    private int productId;

    public Feedback(int id, String feedback, int rating, int userId, int productId) {
        this.id = id;
        this.feedbackDate = LocalDateTime.now();
        this.feedback = feedback;
        this.rating = rating;
        this.userId = userId;
        this.productId = productId;
    }

    public Feedback(int id, LocalDateTime feedbackDate, String feedback, int rating, int userId, int productId) {
        this.id = id;
        this.feedbackDate = feedbackDate;
        this.feedback = feedback;
        this.rating = rating;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }
}
