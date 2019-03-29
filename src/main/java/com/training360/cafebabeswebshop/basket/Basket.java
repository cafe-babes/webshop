package com.training360.cafebabeswebshop.basket;

public class Basket {
    private long id;
    private long userId;
    private long productId;
    private int pieces;

    public Basket(long id, long userId, long productId, int pieces) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.pieces = pieces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
