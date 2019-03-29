package com.training360.cafebabeswebshop.product;

public class ProductValidator {

    private ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValidProduct(Product product){
        return isValid(product.getCode()) &&
                isValid(product.getName()) &&
                isValid(product.getAddress()) &&
                isValid(product.getManufacture()) &&
                isValidPrice(product.getPrice()) &&
                isValid(product.getCategory().getName());
    }

    public boolean isValid(String str) {
        return (str != null && !str.trim().equals(""));
    }

    private boolean isValidPrice(int price){
        return (price > 0 && price <= 2_000_000);
    }
}
