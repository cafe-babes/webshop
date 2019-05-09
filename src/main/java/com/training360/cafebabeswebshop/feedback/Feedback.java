package com.training360.cafebabeswebshop.feedback;

import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.user.User;

import java.time.LocalDateTime;

public class Feedback {

    private long id;
    private LocalDateTime feedbackDate;
    private String feedbackText;
    private int rating;
    private User user;
    private Product product;

    public Feedback() {
    }

    public Feedback(String feedbackText, int rating, User user, Product product) {
        this.feedbackDate = LocalDateTime.now();
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.user = user;
        this.product = product;
    }

    public Feedback(long id, LocalDateTime feedbackDate, String feedbackText, int rating, User user, Product product) {
        this.id = id;
        this.feedbackDate = feedbackDate;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.user = user;
        this.product = product;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public long getId() {
        return id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public int getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackDate=" + feedbackDate +
                ", feedbackText='" + feedbackText + '\'' +
                '}';
    }
}
