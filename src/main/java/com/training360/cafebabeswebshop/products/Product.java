package com.training360.cafebabeswebshop.products;


import java.util.Scanner;

public class Product {
    private long id;
    private String code;
    private String address;
    private String name;
    private String manufacture;
    private long price;

    public Product(long id, String code, String address, String name, String manufacture, long price) {
        this.id = id;
        this.code = code;
        this.address = address;
        this.name = name;
        this.manufacture = manufacture;
        this.price = price;
    }

    public String setAddressUrl(String address) {
        StringBuilder sb = new StringBuilder();
        String[] s = address.split(" ");
        for(String str : s){
            sb.append(str).append("-");
        }
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public long getPrice() {
        return price;
    }
}
