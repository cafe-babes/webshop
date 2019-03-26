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
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class BasketTests {

    @Autowired
    private BasketController basketController;


    @Test
    public void saveBasketItemsTest() {

        basketController.saveBasketItemAndGetId("surf_killer", new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        basketController.saveBasketItemAndGetId("surf_waver", new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        List<BasketItem> list = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        assertEquals(2, list.size());
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("surf_killer"));
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("surf_waver"));
    }

    @Test
    public void listBasketTest() {

        List<BasketItem> baskets = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        assertEquals(1, baskets.size());
    }

    @Test
    public void deleteBasketTest() {

        basketController.deleteBasket(new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        assertEquals(0, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());

    }

    @Test
    public void deleteOneItemTest() {

        basketController.saveBasketItemAndGetId("surf_killer", new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        basketController.saveBasketItemAndGetId("wawe_peak", new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        basketController.deleteOneItem(new TestingAuthenticationToken("user", "user", "ROLE_USER"), "wawe_peak");

        assertEquals(1, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
        assertEquals("surf_killer", basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).get(0).getAddress());
    }


}

