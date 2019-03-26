package com.training360.cafebabeswebshop.product;

public class ProductValidator {

    private ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValidProduct(Product product){
        return isValidCode(product.getCode()) &&
                isValidName(product.getName()) &&
                isValidAddress(product.getAddress()) &&
                isValidManufacture(product.getManufacture()) &&
                isValidPrice(product.getPrice());
    }

    public boolean isValidAddress(String address){
        return address != null && !address.trim().equals("");
    }

    private boolean isValidCode(String code) {
        return (code != null && !code.trim().equals(""));
    }

    private boolean isValidName(String name){
        return (name != null && !name.trim().equals(""));
    }

    private boolean isValidManufacture(String manufacture){
        return (manufacture != null && !manufacture.trim().equals(""));
    }

    private boolean isValidPrice(int price){
        return (price > 0 && price <= 2_000_000);
    }
}
