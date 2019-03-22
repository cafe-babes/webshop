package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.Basket;
import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.product.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
    @SpringBootTest
    @Sql(scripts = "/initBasket.sql")
    public class BasketTests {

        @Autowired
        private BasketController basketController;


        @Test
        public void generateBasket(){

//            basketController.saveBasketItemAndGetId("basket01", new Basket(0, 5, 5));
//            List<Basket> baskets = basketController.getBasketItems(new Authentication() {
//            })



    }
}
