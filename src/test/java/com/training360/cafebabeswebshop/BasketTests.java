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
    @Autowired
    private UserController userController;
    @Autowired
    private ProductController productController;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BasketService basketService;



    @Test
    public void listBasketTest() {

        List<BasketItem> baskets = basketController.getBasketItems(new TestingAuthenticationToken("szepi", "szi", "ROLE_USER"));

        assertEquals(1, baskets.size());
    }

//    @Test
//    public void deleteBasketTest() {
//        long idUser = userService.insertUserAndGetId(new User(1, "Thomas Mann", "thomas.mann@gmail.com", "tm001", "tm001", 1, "ROLE_USER", "ACTIVE"));
//        long idProduct = productService.saveProductAndGetId(new Product(4, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 119000, "ACTIVE"));
//
//    }

    @Test
    public void deleteOneItemTest() {
        long idUser = userService.insertUserAndGetId(new User(1, "Thomas Mann", "thomas.mann@gmail.com", "tm001", "tm001", 1, "ROLE_USER", "ACTIVE"));
        long idProduct = productService.saveProductAndGetId(new Product(1, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 119000, "ACTIVE"));
        long idProduct2 = productService.saveProductAndGetId(new Product(2, "ZZZ111", "sword_fish", "Sword Fish", "cafebabes", 139000, "ACTIVE"));

        Basket basket1 = new Basket(1, idUser, idProduct);
        basketController.saveBasketItemAndGetId("wawe_blade", new TestingAuthenticationToken("tm001", "tm001", "ROLE_USER"));
        basketController.saveBasketItemAndGetId("sword_fish", new TestingAuthenticationToken("tm001", "tm001", "ROLE_USER"));
        basketController.deleteOneItem(new TestingAuthenticationToken("tm001", "tm001", "ROLE_USER"), "sword_fish");

        List<BasketItem> basketItems = basketController.getBasketItems(new TestingAuthenticationToken("tm001", "tm001", "ROLE_USER"));

        assertEquals(1, basketItems.size());
    }


}

