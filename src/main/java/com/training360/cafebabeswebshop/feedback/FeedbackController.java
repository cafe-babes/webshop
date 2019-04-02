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
    public ResultStatus giveAFeedback(@RequestBody Feedback feedback){
        if(feedback.getFeedback().matches("<[^>]*>")){
            return new ResultStatus(ResultStatusEnum.NOT_OK,"HTML kód nem megengedett");
        }
        if(feedbackService.giveAFeedback(feedback)){
            return new ResultStatus(ResultStatusEnum.OK, "Az értékelést megkaptuk, köszönjük.");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK,"Még nem szállítottunk ilyen terméket Önnek.");
    }

    @RequestMapping("/feedback/{productId}")
    public List<Feedback> listFeedBacksByProductId(@PathVariable long productId){
        return feedbackService.listFeedBacksByProductId(productId);
    }

    @DeleteMapping("/feedback/{id}")
    public void deleteFeedbackById(@PathVariable long id){
        feedbackService.deleteFeedbackById(id);
    }



}
