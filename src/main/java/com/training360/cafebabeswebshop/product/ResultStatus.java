package com.training360.cafebabeswebshop.product;

public class ResultStatus<T> {

    private T type;
    private ResultStatusEnum status;
    private String message;

    public ResultStatus(ResultStatusEnum status, String message) {
        this.status = status;
        this.message = message;
    }

    public T get() {
        return type;
    }

    public void set(T type) {
        this.type = type;
    }

    public ResultStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResultStatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
