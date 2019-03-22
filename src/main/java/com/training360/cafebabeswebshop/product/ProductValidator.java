package com.training360.cafebabeswebshop.product;

import java.util.List;

public class ProductValidator {

    private ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValidAddressPresent(String address){
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

    public boolean isValidProduct(Product product){
        if(
                isValidCode(product.getCode()) &&
                isValidName(product.getName()) &&
                isValidAddress(product.getAddress()) &&
                isValidManufacture(product.getManufacture()) &&
                isValidPrice(product.getPrice())
                    )
            return true;
        else {
            return false;
        }
    }

    public Product isValidProduct2(Product product){
                isValidCode(product.getCode());
                isValidName(product.getName());
                isValidAddress(product.getAddress());
                isValidManufacture(product.getManufacture());
                isValidPrice(product.getPrice());
            return product;
    }

    private boolean isValidCode(String code) {
        boolean codeAlreadyExists = false;
        for (Product p: productService.getProducts()) {
            if (p.getCode().equals(code)){
                codeAlreadyExists = true;
            }
        }

        return (code != null && !code.trim().equals("") && !codeAlreadyExists);
    }

    private boolean isValidAddress(String address) {
        boolean addressAlreadyExists = false;
        for (Product p: productService.getProducts()) {
            if (p.getAddress().equals(address)){
                addressAlreadyExists = true;
            }
        }
        return (address != null && !address.trim().equals("") && !addressAlreadyExists);
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
