package com.training360.cafebabeswebshop.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class FeedbackController {

    FeedbackService feedbackService;

    FeedbackValidator feedbackValidator;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
        feedbackValidator = new FeedbackValidator();
    }

    @PostMapping("/feedback")
    public ResultStatus giveAFeedback(@RequestBody Feedback feedback){
        if(feedbackValidator.containsHTMLCode(feedback)){
            return new ResultStatus(ResultStatusEnum.NOT_OK,"HTML kód nem megengedett");
        }
        if(feedbackService.giveAFeedback(feedback)){
            return new ResultStatus(ResultStatusEnum.OK, "Az értékelést megkaptuk, köszönjük.");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK,"Még nem szállítottunk ilyen terméket Önnek.");
    }

    @GetMapping("/feedback/{productId}")
    public List<Feedback> listFeedBacksByProductId(@PathVariable long productId){
        return feedbackService.listFeedBacksByProductId(productId);
    }

    @DeleteMapping("/feedback/{id}")
    public void deleteFeedbackById(@PathVariable long id){
        feedbackService.deleteFeedbackById(id);
    }



}
