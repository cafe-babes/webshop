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

		Product product = products.stream().filter(e -> e.getAddress().equals("balaton_shark")).findFirst().get();
		long id = product.getId();

		productController.deleteLocation(id);

		products = productController.getProducts();

		assertEquals(2, products.size());
		assertEquals("DELETED", products.get(1).getProduct_status());
	}
}
