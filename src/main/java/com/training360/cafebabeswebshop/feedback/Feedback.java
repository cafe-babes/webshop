package com.training360.cafebabeswebshop.feedback;

import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.user.User;

import java.time.LocalDateTime;

public class Feedback {

    private int id;
    private LocalDateTime feedbackDate;
    private String feedback;
    private int rating;
    private User user;
    private Product product;

    public Feedback(int id, String feedback, int rating, User user, Product product) {
        this.id = id;
        this.feedbackDate = LocalDateTime.now();
        this.feedback = feedback;
        this.rating = rating;
        this.user = user;
        this.product = product;
    }

    public Feedback(int id, LocalDateTime feedbackDate, String feedback, int rating, User user, Product product) {
        this.id = id;
        this.feedbackDate = feedbackDate;
        this.feedback = feedback;
        this.rating = rating;
        this.user = user;
        this.product = product;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
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

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
