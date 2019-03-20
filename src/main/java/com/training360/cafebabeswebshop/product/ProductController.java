package com.training360.cafebabeswebshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{address}")
    public Product getProduct(@PathVariable String address){
        return productService.getProduct(address);
    }

    @GetMapping("/products")
    public List<Product> getProducts()

}