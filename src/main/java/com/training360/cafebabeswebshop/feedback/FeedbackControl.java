package com.training360.cafebabeswebshop.feedback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackControl {

    @Autowired
    FeedbackService feedbackService;

//    @PostMapping("/feedback/{}/{}")
//    public void giveAFeedback(@PathVariable long id, @PathVariable String address){
//
//    }

    @GetMapping("/feedback/{id}")
    public List<Feedback> listFeedBacksByProductId(@PathVariable int productId){
        return feedbackService.listFeedBacksByProductId(productId);
    }


}
