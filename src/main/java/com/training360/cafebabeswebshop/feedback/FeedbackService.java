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

    public boolean giveAFeedback(Feedback feedback) {

        long userId = feedback.getUser().getId();
        long productId = feedback.getProduct().getId();

        boolean feedbackWasSuccessful = false;

        if (feedbackDao.alreadyGaveAFeedback(userId, productId)) {

            feedbackDao.updateFeedback(feedback);
            feedbackWasSuccessful = true;

        } else if (feedbackDao.userCanGiveAFeedback(userId, productId)) {

            feedbackDao.giveAFeedback(feedback);
            feedbackWasSuccessful = true;

        }
        return feedbackWasSuccessful;
    }

    public void deleteFeedbackById(long id) {
        feedbackDao.deleteFeedbackById(id);
    }

}
