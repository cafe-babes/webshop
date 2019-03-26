package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.basket.BasketItem;
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
    public void testSaveBasketItemAndGetId() {
        // When
        long newId = basketController.saveBasketItemAndGetId("surf_killer", new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        long anotherNewId = basketController.saveBasketItemAndGetId("greedy_beaver", new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        List<BasketItem> list = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        // Then
        assertEquals(4, list.size());
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("surf_killer"));
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("greedy_beaver"));
    }

    @Test
    public void testGetBasketItems() {
        assertEquals(2, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
    }

    @Test
    public void testDeleteBasket() {
        basketController.deleteBasket(new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        assertEquals(0, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
    }

    @Test
    public void testDeleteOneItem() {
        basketController.deleteOneItem(new TestingAuthenticationToken("user", "user", "ROLE_USER"), "surf_slayer2");

        assertEquals(1, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
        assertEquals("surf_killer", basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).get(0).getAddress());
    }
}

