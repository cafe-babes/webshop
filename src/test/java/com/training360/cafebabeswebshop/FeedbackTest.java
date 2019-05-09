package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.feedback.Feedback;
import com.training360.cafebabeswebshop.feedback.FeedbackController;
import com.training360.cafebabeswebshop.feedback.FeedbackService;
import com.training360.cafebabeswebshop.feedback.ResultStatus;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductService;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class FeedbackTest {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackController feedbackController;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Test
    public void testListFeedBacksByProductId() {

        List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(1);
        assertEquals(3, feedbacks.get(0).getUser().getId());
        assertEquals("Never a better shop!", feedbacks.get(0).getFeedbackText());

    }

    @Test
    public void testGiveAFeedBack() {

        User exampleUser = userService.getUserById(2);
        Product exampleproduct = productService.getProductById(8);

        feedbackService.giveAFeedback(new Feedback("Awesome!", 5, exampleUser,
                exampleproduct));

        List<Feedback> feedbacks = feedbackController.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(exampleUser.getUserName(), feedbacks.get(0).getUser().getUserName());
        assertEquals("Awesome!", feedbacks.get(0).getFeedbackText());
        assertEquals(8, feedbacks.get(0).getProduct().getId());
        assertEquals(5, feedbacks.get(0).getRating());

    }

    @Test
    public void testDeleteFeedBackById() {

//        Given (we have ONE product of which we have ONE feedback)

        Product exampleproduct = productService.getProductById(1);

        List<Feedback> feedbackListOfExampleProduct = feedbackService.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(1, feedbackListOfExampleProduct.size());


//        When (deleting ONE feedback by it's ID )

        long idOfExampleFeedback = feedbackListOfExampleProduct.get(0).getId();

        feedbackController.deleteFeedbackById(idOfExampleFeedback);

//      Then  (the list of Feedbacks decreases by one as well)
        feedbackListOfExampleProduct = feedbackService.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(feedbackListOfExampleProduct, Collections.emptyList());

    }

    @Test
    public void testUserDidNotReceiveSuchProductThereforeCanNotGiveAnyFeedback() {

//      Given  (A user whom the company haven't shipped the given product yet)
        User exampleUser = userService.getUserById(1);
        Product exampleproduct = productService.getProductById(2);

//      When (The user tries to send a feedback)
        feedbackController.giveAFeedback(new Feedback("Awesome!", 5, exampleUser,
                exampleproduct));

        List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(exampleproduct.getId());

//      Then (It will have no effect. The feedback list for the product will remain empty)
        assertEquals(feedbacks, Collections.emptyList());

    }

    @Test
    public void testSecondFeedbackForAProductFromOneUserWillBeAnUpdateOfTheFirstOneNotANewFeedback() {


//      Given (One User & OneProduct)
        User exampleUser = userService.getUserById(2);
        Product exampleproduct = productService.getProductById(8);


//      When (Gives Two feedback for the product)
        feedbackService.giveAFeedback(new Feedback("Awesome!", 4, exampleUser,
                exampleproduct));

        List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(1, feedbacks.size());
        assertEquals("Awesome!", feedbacks.get(0).getFeedbackText());
        assertEquals(4, feedbacks.get(0).getRating());

        feedbackService.giveAFeedback(new Feedback("Awesome #2!", 5, exampleUser,
                exampleproduct));

//        Then (the second feedback will serve only as an update of the first one)
        feedbacks = feedbackService.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(1, feedbacks.size());
        assertEquals("Awesome #2!", feedbacks.get(0).getFeedbackText());
        assertEquals(5, feedbacks.get(0).getRating());

    }

    @Test
    public void testGivingHTMLCodeAsFeedbackIsNotAccepted() {


//      Given (One User & OneProduct)
        User exampleUser = userService.getUserById(2);
        Product exampleproduct = productService.getProductById(8);


//      When (User gives a feedback that contains html code)
        ResultStatus rs = feedbackController.giveAFeedback(new Feedback("Awesome shop! <span style=\"color: #3333\">Action</span>!", 4, exampleUser,
                exampleproduct));

//      Then (An error message appears)
        boolean expected = rs.getMessage().equals("HTML kód nem megengedett");

        assertTrue(expected);
    }
    @Test
    public void testUserCanModifyOnlyHisReview() {

//      Given (Two feedback for a product)
        User exampleUser = userService.getUserById(2);
        User exampleUser2 = userService.getUserById(1);
        Product exampleproduct = productService.getProductById(8);

        feedbackService.giveAFeedback(new Feedback("Awesome!", 5, exampleUser,
                exampleproduct));

        feedbackService.giveAFeedback(new Feedback("Awesome2!", 5, exampleUser2,
                exampleproduct));

        List<Feedback> feedbacks = feedbackController.listFeedBacksByProductId(exampleproduct.getId());

        assertEquals(2, feedbacks.size());
        assertTrue(feedbacks.stream().map(Feedback::getFeedbackText).collect(Collectors.toList()).contains("Awesome!"));
        assertTrue(feedbacks.stream().map(Feedback::getFeedbackText).collect(Collectors.toList()).contains("Awesome2!"));

//      When (exampleUser2 gives a feedback it will affect only his)

        feedbackService.giveAFeedback(new Feedback("Awesome3!", 5, exampleUser2,
                exampleproduct));

        feedbacks = feedbackController.listFeedBacksByProductId(exampleproduct.getId());


        assertEquals(2, feedbacks.size());
        assertEquals("Awesome!", feedbacks.get(0).getFeedbackText());
        assertEquals("Awesome3!", feedbacks.get(1).getFeedbackText());

    }

}
