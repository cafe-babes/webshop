package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class CafebabeswebshopApplicationTests {

	@Autowired
	private ProductController productController;


	@Test
	public void contextLoads() {
		productController.saveProductAndGetId(new Product(5, "25KA14", "balaton_shark", "Balaton Shark", "cafebabes", 200000, "ACTIVE"));
		List<Product> products = productController.getProducts();

		assertEquals(2, products.size());

	}

	@Test
	public void testDeleteProduct(){
		//Given
		productController.saveProductAndGetId(new Product(5, "25KA14", "balaton_shark", "Balaton Shark", "cafebabes", 200000, "ACTIVE"));

		List<Product> products = productController.getProducts();

		Product product = products.stream().filter(e -> e.getAddress().equals("balaton_shark")).findAny().get();
		long id = product.getId();

		productController.deleteLocation(id);

		products = productController.getProducts();

		assertEquals(2, products.size());
		assertEquals("DELETED", products.get(0).getProduct_status());
	}

	@Test
	public void testProductLoad(){
		//Given
		productController.saveProductAndGetId(new Product(6, "LLL333", "sea_snake", "Sea Snake", "cafebabes", 120000, "ACTIVE"));

		// When
		List<Product> products = productController.getProducts();
		products = productController.getProducts();

		// Then
		assertEquals(2, products.size());
	}

}
