package com.training360.cafebabeswebshop;
import com.training360.cafebabeswebshop.category.Category;
import com.training360.cafebabeswebshop.category.CategoryService;
import com.training360.cafebabeswebshop.feedback.Feedback;
import com.training360.cafebabeswebshop.feedback.FeedbackService;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class FeedbackTest {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

@Test
    public void testListFeedBacksByProductId(){

    List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(1);
    assertEquals(3,feedbacks.get(0).getUser().getId());
    assertEquals("Never a better shop!",feedbacks.get(0).getFeedback());

}

    @Test
    public void testGiveAFeedBack(){

        User exampleUser = userService.listUsers().get(0);
        Product exampleproduct = productService.getProductById(1);

        feedbackService.giveAFeedback(new Feedback(5,"Awesome!",5,exampleUser,
                exampleproduct));

        List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(exampleproduct .getId());

        assertEquals(exampleUser.getUserName(), feedbacks.get(0).getUser().getUserName());
        assertEquals("Awesome!", feedbacks.get(0).getFeedback());

    }


}
