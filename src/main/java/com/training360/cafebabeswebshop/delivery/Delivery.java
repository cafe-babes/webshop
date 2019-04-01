package com.training360.cafebabeswebshop.delivery;

public class Delivery {

    private long deliveryId;
    private String deliveryAddress;
    private long userId;

    public Delivery(long deliveryId, String deliveryAddress, long userId) {
        this.deliveryId = deliveryId;
        this.deliveryAddress = deliveryAddress;
        this.userId = userId;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
