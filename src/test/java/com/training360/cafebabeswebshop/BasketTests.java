package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.Basket;
import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.basket.BasketItem;
import com.training360.cafebabeswebshop.basket.BasketService;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductController;
import com.training360.cafebabeswebshop.product.ProductService;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserController;
import com.training360.cafebabeswebshop.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init_basket_table.sql")
public class BasketTests {

    @Autowired
    private BasketController basketController;


    @Test
    public void saveBasketItemsTest() {

        basketController.saveBasketItemAndGetId("surf_killer", new TestingAuthenticationToken("chch", "ch01", "ROLE_USER"));
        basketController.saveBasketItemAndGetId("wawe_peak", new TestingAuthenticationToken("chch", "ch01", "ROLE_USER"));

        assertEquals(2, basketController.getBasketItems(new TestingAuthenticationToken("chch", "ch01", "ROLE_USER")).size());
    }

    @Test
    public void listBasketTest() {

        List<BasketItem> baskets = basketController.getBasketItems(new TestingAuthenticationToken("szepi", "szi", "ROLE_USER"));

        assertEquals(1, baskets.size());
    }

    @Test
    public void deleteBasketTest() {

        basketController.deleteBasket(new TestingAuthenticationToken("ff", "ff", "ROLE_USER"));

        assertEquals(0, basketController.getBasketItems(new TestingAuthenticationToken("ff", "ff", "ROLE_USER")).size());

    }

    @Test
    public void deleteOneItemTest() {

        basketController.saveBasketItemAndGetId("surf_killer", new TestingAuthenticationToken("chch", "ch01", "ROLE_USER"));
        basketController.saveBasketItemAndGetId("wawe_peak", new TestingAuthenticationToken("chch", "ch01", "ROLE_USER"));

        basketController.deleteOneItem(new TestingAuthenticationToken("chch", "ch01", "ROLE_USER"), "wawe_peak");

        assertEquals(1, basketController.getBasketItems(new TestingAuthenticationToken("chch", "ch01", "ROLE_USER")).size());
        assertEquals("surf_killer", basketController.getBasketItems(new TestingAuthenticationToken("chch", "ch01", "ROLE_USER")).get(0).getAddress());
    }


}

