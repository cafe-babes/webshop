package com.training360.cafebabeswebshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    private ProductValidator validator;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{address}")
    public Object getProduct(@PathVariable String address){
        validator = new ProductValidator(productService);
        if (validator.isValidAddress(address)){
            return productService.getProduct(address);
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid address");
        }
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
       return productService.getProducts();
    }

}