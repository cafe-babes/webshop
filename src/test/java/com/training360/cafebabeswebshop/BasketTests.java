package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.basket.Basket;
import com.training360.cafebabeswebshop.basket.BasketController;
import com.training360.cafebabeswebshop.basket.BasketItem;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductController;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
    @SpringBootTest
    @Sql(scripts = "/initBasket.sql")
    public class BasketTests {

         @Autowired
        private BasketController basketController;
         @Autowired
        private UserController userController;
        @Autowired
        private ProductController productController;

        @Test
        public void generateBasket(){
            userController.insertUserAndGetId(new User(1, "Thomas Mann", "thomas.mann@gmail.com", "tm001", "tm001", 1, "ROLE_USER", "ACTIVE"));
            userController.insertUserAndGetId(new User(1, "Ernest Hemingway", "ernest.ham@gmail.com", "eh001", "eh001", 1, "ROLE_USER", "ACTIVE"));
            List<User> users = userController.listUsers();

            User user1 = users.stream().filter(e -> e.getEmail().equals("thomas.mann@gmail.com")).findAny().get();
            User user2 = users.stream().filter(e -> e.getEmail().equals("ernest.ham@gmail.com")).findAny().get();

            long idUser1 = user1.getId();
            long idUser2 = user2.getId();

            productController.saveProductAndGetId(new Product(0, "LLL333", "sea_snake", "Sea Snake", "cafebabes", 120000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(0, "ZZZ111", "sword_fish", "Sword Fish", "cafebabes", 139000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(0, "YYY222", "sea_star", "Sea Star", "cafebabes", 99000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(0, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 119000, "ACTIVE"));
            List<Product> products = productController.getProducts();
            Product product1 = products.stream().filter(e -> e.getAddress().equals("sea_star")).findAny().get();
            Product product2 = products.stream().filter(e -> e.getAddress().equals("wawe_blade")).findAny().get();
            long idProduct1 = product1.getId();
            long idProduct2 = product2.getId();

//            basketController.saveBasketItemAndGetId("basket01", new Basket(1, idUser1, idProduct1));
//            basketController.saveBasketItemAndGetId("basket02", new Basket(1, idUser2, idProduct2));
//            List<BasketItem> baskets = basketController.getBasketItems(new TestingAuthenticationToken("admin2", "admin2", "ROLE_ADMIN"));

            assertEquals(100 , idProduct1);
            assertEquals(100 , idProduct2);

            }

    }

