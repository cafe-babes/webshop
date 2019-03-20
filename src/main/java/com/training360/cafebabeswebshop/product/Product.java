package com.training360.cafebabeswebshop.product;


public class Product {

    private long id;
    private String code;
    private String address;
    private String name;
    private String manufacture;
    private int price;

    public Product(String code, String address, String name, String manufacture, int price) {
        this.code = code;
        this.address = address;
        this.name = name;
        this.manufacture = manufacture;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
