package com.training360.cafebabeswebshop.order;

public class OrderedProduct {

    private long id;
    private long productId;
    private long orderId;
    private long orderingPrice;
    private String orderingName;

    public OrderedProduct(long id, long productId, long orderId, long orderingPrice, String orderingName) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.orderingPrice = orderingPrice;
        this.orderingName = orderingName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderingPrice() {
        return orderingPrice;
    }

    public void setOrderingPrice(long orderingPrice) {
        this.orderingPrice = orderingPrice;
    }

    public String getOrderingName() {
        return orderingName;
    }

    public void setOrderingName(String orderingName) {
        this.orderingName = orderingName;
    }
}
