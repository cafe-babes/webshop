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
		List<Product> products = productController.getProducts(null);

		assertEquals(2, products.size());
	}

	@Test
	public void testDeleteProduct(){
		//Given
		productController.saveProductAndGetId(new Product(5, "25KA14", "balaton_shark", "Balaton Shark", "cafebabes", 200000, "ACTIVE"));

		List<Product> products = productController.getProducts(null);

		Product product = products.stream().filter(e -> e.getAddress().equals("balaton_shark")).findAny().get();
		long id = product.getId();

		productController.deleteProduct(id);

		products = productController.getProducts(null);

		assertEquals(2, products.size());
		assertEquals("balaton_shark", products.get(1).getAddress());
		assertEquals("DELETED", products.get(1).getProductStatus());
	}

	@Test
	public void testProductAdministration(){
		//Given
		productController.saveProductAndGetId(new Product(0, "LLL333", "sea_snake", "Sea Snake", "cafebabes", 120000, "ACTIVE"));
		productController.saveProductAndGetId(new Product(0, "ZZZ111", "sword_fish", "Sword Fish", "cafebabes", 139000, "ACTIVE"));
		productController.saveProductAndGetId(new Product(0, "YYY222", "sea_star", "Sea Star", "cafebabes", 99000, "ACTIVE"));
		productController.saveProductAndGetId(new Product(0, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 119000, "ACTIVE"));
			// Az id és a product_status nem kerül lementésre, mert azt az SQL álllítja be

		// When
		List<Product> products = productController.getProducts(null);
		Product product = products.stream().filter(e -> e.getAddress().equals("sea_star")).findAny().get();
		Product product2 = products.stream().filter(e -> e.getAddress().equals("wawe_blade")).findAny().get();
		long id = product.getId();
		long id2 = product2.getId();

		productController.deleteProduct(id);
		productController.updateProducts(id2, new Product(0, "XXX333", "wawe_blade", "Wawe Blade", "cafebabes", 222_222, "ACTIVE"));
		products = productController.getProducts(null);

		// Then
		assertEquals("bala_lala", products.get(0).getAddress());
		assertEquals("sea_snake", products.get(1).getAddress());
		assertEquals("sea_star", products.get(2).getAddress());
		assertEquals("sword_fish", products.get(3).getAddress());
		assertEquals("wawe_blade", products.get(4).getAddress());

		assertEquals(5, products.size());
		assertEquals("DELETED", products.get(2).getProductStatus());
		assertEquals(222_222, products.get(4).getPrice());
	}

	@Test
	public void testBasketMethods(){
		// Given

		// When

		// Then



	}
}
