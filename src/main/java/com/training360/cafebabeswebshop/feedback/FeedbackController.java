package com.training360.cafebabeswebshop.feedback;


import com.training360.cafebabeswebshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/feedback")
    public void giveAFeedback(@RequestBody Feedback feedback){
        feedbackService.giveAFeedback(feedback);
    }

    @RequestMapping("/feedback/{productId}")
    public List<Feedback> listFeedBacksByProductId(@PathVariable long productId){
        return feedbackService.listFeedBacksByProductId(productId);
    }


}
