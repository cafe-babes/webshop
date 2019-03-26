package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductController;
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
@Sql(scripts = "/init.sql")
public class ProductTests {

	@Autowired
	private ProductController productController;


	@Test
	public void testSaveProductAndGetId() {
		//Given
		List<Product> products = productController.getProducts(new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN"));
		// When
		productController.saveProductAndGetId(new Product(21, "25KA14", "balaton_shark", "Balaton Shark", "cafebabes", 200000, "ACTIVE"));
		//Then
		List<Product> products2 = productController.getProducts(new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN"));
		assertEquals(20, products.size());
		assertEquals(21, products2.size());
		assertEquals("balaton_shark", products2.get(0).getAddress());
	}

	@Test
	public void testDeleteProduct(){
		// When
		productController.deleteProduct(7);
		List<Product> products = productController.getProducts(new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN"));
		//Then
		assertEquals("DELETED", products.stream().filter(e -> e.getId() == 7 ).findAny().get().getProductStatus());
	}

	@Test
	public void testUpdateProducts(){
		// When
		productController.updateProducts(1, new Product(0, "ST001", "surf_trainer", "Surf Trainer", "Blue Sea Watersports", 111_111, "ACTIVE"));
		// Then
		assertEquals("Surf Trainer", productController.findById(1).getName());
		assertEquals("ST001", productController.findById(1).getCode());
		assertEquals("surf_trainer", productController.findById(1).getAddress());
		assertEquals("Blue Sea Watersports", productController.findById(1).getManufacture());
		assertEquals(111_111, productController.findById(1).getPrice());
		assertEquals("ACTIVE", productController.findById(1).getProductStatus());
	}

//	@Test
//	public void testBasketMethods(){
//		// Given
//
//		// When
//
//		// Then
//
//
//
//	}
}
