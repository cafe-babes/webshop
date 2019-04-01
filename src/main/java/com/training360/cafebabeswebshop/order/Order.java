package com.training360.cafebabeswebshop.order;

import java.time.LocalDateTime;

public class Order {

    private long id;
    private LocalDateTime purchaseDate;
    private long userId;
    private long total;
    private long sumQuantity;
    private OrderStatus orderStatus;
    private long deliveryId;

    public Order() {
    }

    public Order(long id, long userId, long total, long sumQuantity, String status, long deliveryId) {
        this(id, LocalDateTime.now(), userId, total, sumQuantity, status, deliveryId);
    }

    public Order(long id, LocalDateTime purchaseDate, long userId, long total, long sumQuantity, String status, long deliveryId) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.userId = userId;
        this.total = total;
        this.sumQuantity = sumQuantity;
        this.orderStatus = OrderStatus.valueOf(status);
        this.deliveryId = deliveryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(long sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String toString() {
        return id + " " + purchaseDate + " " + userId + " " + total + " " + sumQuantity + " " + orderStatus + " " + deliveryId;
    }
}
