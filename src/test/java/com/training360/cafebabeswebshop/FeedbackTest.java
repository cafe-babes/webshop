package com.training360.cafebabeswebshop;
import com.training360.cafebabeswebshop.category.Category;
import com.training360.cafebabeswebshop.feedback.Feedback;
import com.training360.cafebabeswebshop.feedback.FeedbackService;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.user.User;
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

@Test
    public void testListFeedBacksByProductId(){

    List<Feedback> feedbacks = feedbackService.listFeedBacksByProductId(1);
    assertEquals(3,feedbacks.get(0).getUser().getId());
    assertEquals("Never a better shop!",feedbacks.get(0).getFeedback());

}

//    @Test
//    public void testGiveAFeedBack(){
//
//        User user = new User(5,"Ernő","alma@alma.com","ernőke","ernőő",
//                1,"ROLE_ADMIN","ACTIVE");
//        Category category = new Category(5,"balzsamecet",2);
//        Product product = new Product(99,"sss","sssss","sssssssss","aaaaaa",
//                500000,"ACTIVE",category);
//        feedbackService.giveAFeedback(new Feedback(5,"Awesome!",5,user,
//                product));
//    }


}
