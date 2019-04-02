package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.category.Category;
import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class ProductTests {

	@Autowired
	private ProductController productController;


	@Test
	public void testGetProduct(){
		// When
		List<Product> products = productController.getProducts();
		//Then
		assertEquals(14, products.size());
	}

	@Test
	public void testSaveProductAndGetId() {
		//Given
		List<Product> products = productController.getProducts();
		// When
		productController.saveProductAndGetId(new Product(21, "25KA14", "balaton_shark", "Balaton Shark", "cafebabes", 200000, "ACTIVE", new Category(1, "pretty", 1)));
		//Then
		List<Product> products2 = productController.getProducts();
		assertEquals(14, products.size());
		assertEquals(15, products2.size());
		assertEquals("balaton_shark", products2.get(0).getAddress());
	}

	@Test
	public void testUpdateProducts(){
		// When
		productController.updateProducts(1, new Product(0, "ST001", "surf_trainer", "Surf Trainer", "Blue Sea Watersports", 111_111, "ACTIVE", new Category(1, "pretty", 1)));
		// Then
		assertEquals("Surf Trainer", productController.getProductById(1).getName());
		assertEquals("ST001", productController.getProductById(1).getCode());
		assertEquals("surf_trainer", productController.getProductById(1).getAddress());
		assertEquals("Blue Sea Watersports", productController.getProductById(1).getManufacture());
		assertEquals(111_111, productController.getProductById(1).getPrice());
		assertEquals("ACTIVE", productController.getProductById(1).getProductStatus());
	}

	@Test
	public void testGetProducts(){
		// When
		List<Product> productListForAdmin = productController.getProducts();
		List<Product> productListForUser = productController.getProducts();
		// Then
		assertEquals(14, productListForAdmin.size());
		assertEquals(14, productListForUser.size());
	}

	@Test
	public void testGetProductsWithStartAndSize(){
		// When
		List<Product> productsPart = productController.getProductsWithStartAndSize(1,10, null);
		// Then
		assertEquals(10, productsPart.size());
	}

	@Test
	public void testDeleteProduct(){
		// When
		List<Product> products = productController.getProducts();
		productController.deleteProduct(7);
		List<Product> products2 = productController.getProducts();

		//Then
		assertEquals(products2.size(), products.size()-1);
	}

	@Test
	public void getProductById(){
		// When
		Product searchedProduct = productController.getProductById(14);
		//Then
		assertEquals(14, searchedProduct.getId());
		assertEquals("Pyzel", searchedProduct.getManufacture());
		assertEquals("Bastard", searchedProduct.getName());
	}

	@Test
	public void testListAdviceProducts(){
		// When
		List<Product> productList = productController.listAdvicedProducts();
		//Then
		assertEquals(productList.size(),3 );
	}

	@Test
	public void testOfferedProductsStatusIsActive(){
		// When
		List<Product> productList = productController.listAdvicedProducts();
		//Then
		int sizeOfACTIVEProductList = productList.stream().filter(p -> p.getProductStatus().equals("ACTIVE")).collect(Collectors.toList()).size();
		assertEquals(productList.size(), sizeOfACTIVEProductList);
	}


}
