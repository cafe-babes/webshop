package com.training360.cafebabeswebshop.product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductValidator {

    private ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValidProduct(Product product){
        return isEmpty(product.getCode()) &&
                isEmpty(product.getName()) &&
                isEmpty(product.getAddress()) &&
                isEmpty(product.getManufacture()) &&
                isValidPrice(product.getPrice());
    }

    public boolean isEmpty(String str) {
        List<String> address = productService.getProducts().stream().map(p -> p.getAddress()).collect(Collectors.toList());
        return str == null || str.trim().equals("") || !address.contains(str);
    }

    private boolean isValidPrice(int price){
        return (price > 0 && price <= 2_000_000);
    }
}
