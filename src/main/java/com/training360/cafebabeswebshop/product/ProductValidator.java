package com.training360.cafebabeswebshop.product;

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
        return (str != null && !str.trim().equals(""));
    }

    private boolean isValidPrice(int price){
        return (price > 0 && price <= 2_000_000);
    }
}
