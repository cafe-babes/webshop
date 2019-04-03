package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.basket.BasketItem;
import com.training360.cafebabeswebshop.product.Product;
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
    public void testGetBasketItems() {
        // When
        List<BasketItem> basketItems = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        // Then
        assertEquals(2, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
    }

    @Test
    public void testDeleteBasket() {

        // When
        basketController.deleteBasket(new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        List<BasketItem> basketItems = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        // Then
        assertEquals(0, basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER")).size());
    }

    @Test
    public void testDeleteOneItem() {
        // Given
        List<BasketItem> basketItemsBeforeDelete = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        assertEquals(2, basketItemsBeforeDelete.size());
        long productIdNumberOneSurfWaver = basketItemsBeforeDelete.stream().filter(p -> p.getAddress().equals("surf_waver")).findFirst().get().getProductId();
        long productIdNumberTwoSurfSlayer2 = basketItemsBeforeDelete.stream().filter(p -> p.getAddress().equals("surf_slayer2")).findFirst().get().getProductId();

        assertEquals(productIdNumberOneSurfWaver,5);
        assertEquals(productIdNumberTwoSurfSlayer2,3);

        // When
        basketController.deleteOneItem(new TestingAuthenticationToken("user", "user", "ROLE_USER"), "surf_slayer2");
        List<BasketItem> basketItemsAfterDelete = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));

        // Then
        assertEquals(1, basketItemsAfterDelete.size());
        assertEquals("surf_waver", basketItemsAfterDelete.get(0).getAddress());
    }


    @Test
    public void testSaveBasketItemAndGetId() {
        BasketItem basketItemExample = new BasketItem(8,"Funny","funny_bunny", 25800, 2);
        // When
        long anotherNewId = basketController.saveBasketItemAndGetId("funny_bunny", basketItemExample, new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        List<BasketItem> list = basketController.getBasketItems(new TestingAuthenticationToken("user", "user", "ROLE_USER"));
        // Then
        assertEquals(3, list.size());
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("surf_waver"));
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("surf_slayer2"));
        assertTrue(list.stream().map(BasketItem::getAddress).collect(Collectors.toList()).contains("funny_bunny"));

    }


}

