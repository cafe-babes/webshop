package com.training360.cafebabeswebshop.basket;

public class BasketProduct {
    private String name;
    private int price;
    private int amount;
    private String address;

    public BasketProduct(String name, int price, int amount, String address) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
