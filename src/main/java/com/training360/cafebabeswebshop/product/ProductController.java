package com.training360.cafebabeswebshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    private ProductValidator validator = new ProductValidator();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{address}")
    public ResultStatus getProduct(@PathVariable String address){
        try {
            productService.getProduct(validator.isValidAddress(null,address));
            return new ResultStatus(ResultStatusE.OK, "Valid address");
        } catch (IllegalStateException e){
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid address");
        }
    }

    @GetMapping("/products")
    public List<Product> getProducts()

}