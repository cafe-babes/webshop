package com.training360.cafebabeswebshop.basket;

public class BasketItem {
    private long productId;
    private String name;
    private int price;
    private int amount;
    private String address;

    public BasketItem(long productId, String name, int price, int amount, String address) {
        this.productId = productId;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
