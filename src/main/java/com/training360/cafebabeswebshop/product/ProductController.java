package com.training360.cafebabeswebshop.product;

import com.training360.cafebabeswebshop.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{address}")
    public Product getProduct(@RequestBody Product product){
        return productService.getProduct(product.setAddressUrl(product.getAddress()));
    }

}
