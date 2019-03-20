package com.training360.cafebabeswebshop.product;

import java.util.List;

public class ProductValidator {


    public String isValidAddress(List<Product> products, String address){
        boolean presentAddress = false;
        for (Product p: products) {
            if (p.getAddress().equals(address)){
                presentAddress = true;
            }
        }
        if (address == null && address.trim().equals("") && !presentAddress){
            throw new IllegalStateException("Invalid address");
            }
        return address;
    }
}
