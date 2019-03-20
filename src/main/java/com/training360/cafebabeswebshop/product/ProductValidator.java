package com.training360.cafebabeswebshop.product;

import java.util.List;

public class ProductValidator {

    private ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValidAddress(String address){
        boolean presentAddress = false;
        for (Product p: productService.getProducts()) {
            if (p.getAddress().equals(address)){
                presentAddress = true;
            }
        }
        if (address != null && !address.trim().equals("") && presentAddress){
            return true;
            }
        return false;
    }
}
