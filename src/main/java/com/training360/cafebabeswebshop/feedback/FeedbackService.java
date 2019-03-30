package com.training360.cafebabeswebshop.feedback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {


    FeedbackDao feedbackDao;

    public FeedbackService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public List<Feedback> listFeedBacksByProductId(long productId) {
        return feedbackDao.listFeedBacksByProductId(productId);
    }

    public void giveAFeedback(Feedback feedback) {
        feedbackDao.giveAFeedback(feedback);
    }

    public void deleteFeedbackById(long id) {
        feedbackDao.deleteFeedbackById(id);
    }
}
