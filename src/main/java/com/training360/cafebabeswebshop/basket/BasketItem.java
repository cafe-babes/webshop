package com.training360.cafebabeswebshop.basket;

public class BasketItem {
    private long productId;
    private String name;
    private int price;
    private int pieces;


    public BasketItem(long productId, String name, int price, int pieces) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.pieces = pieces;
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

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
