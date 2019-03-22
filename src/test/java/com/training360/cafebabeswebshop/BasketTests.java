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
        public void generateBasketTest(){
            userController.insertUser(new User(1, "Thomas Mann", "thomas.mann@gmail.com", "tm001", "tm001", 1, "ROLE_USER", "ACTIVE"));
            userController.insertUser(new User(1, "Ernest Hemingway", "ernest.ham@gmail.com", "eh001", "eh001", 1, "ROLE_USER", "ACTIVE"));
            userController.insertUser(new User(1, "Admin1", "admin1@gmail.com", "admin2", "admin2", 1, "ROLE_ADMIN", "ACTIVE"));
            List<User> users = userController.listUsers();

            User user1 = users.stream().filter(e -> e.getUserName().equals("tm001")).findAny().get();
            User user2 = users.stream().filter(e -> e.getUserName().equals("eh001")).findAny().get();
            User user3 = users.stream().filter(e -> e.getUserName().equals("admin2")).findAny().get();

            long idUser1 = user1.getId();
            long idUser2 = user2.getId();
            long idUser3 = user3.getId();

            productController.saveProductAndGetId(new Product(1, "LLL333", "sea_snake", "Sea Snake", "cafebabes", 120000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(2, "ZZZ111", "sword_fish", "Sword Fish", "cafebabes", 139000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(3, "YYY222", "sea_star", "Sea Star", "cafebabes", 99000, "ACTIVE"));
            productController.saveProductAndGetId(new Product(4, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 119000, "ACTIVE"));
            List<Product> products = productController.getProducts();
            Product product1 = products.stream().filter(e -> e.getAddress().equals("sea_snake")).findAny().get();
            Product product2 = products.stream().filter(e -> e.getAddress().equals("sword_fish")).findAny().get();
            Product product3 = products.stream().filter(e -> e.getAddress().equals("sea_star")).findAny().get();
            long idProduct1 = product1.getId();
            long idProduct2 = product2.getId();
            long idProduct3 = product2.getId();

            basketController.saveBasketItemAndGetId("sea_snake", new Basket(1, idUser1, idProduct1));
            basketController.saveBasketItemAndGetId("sword_fish", new Basket(2, idUser2, idProduct2));
            basketController.saveBasketItemAndGetId("sea_star", new Basket(3, idUser1, idProduct3));

            List<BasketItem> baskets = basketController.getBasketItems(new TestingAuthenticationToken("tm001", "tm001", "ROLE_USER"));

            assertEquals(2 , baskets.size());
        }




    }

