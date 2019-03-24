package com.training360.cafebabeswebshop.order;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Order {

    private long id;
    private LocalDateTime purchaseDate;
    private long userId;
    private long total;
    private long sumQuantity;
    private OrderStatus orderStatus;

    public Order(long id, long userId, long total, long sumQuantity, String status) {
        this.id = id;
        this.purchaseDate = LocalDateTime.now();
        this.userId = userId;
        this.total = total;
        this.sumQuantity = sumQuantity;
        this.orderStatus = OrderStatus.valueOf(status);
    }

    public Order(long id, LocalDateTime purchaseDate, long userId, long total, long sumQuantity, String status) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.userId = userId;
        this.total = total;
        this.sumQuantity = sumQuantity;
        this.orderStatus = OrderStatus.valueOf(status);
    }

    public Order() {
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
}
