package com.training360.cafebabeswebshop.product;

public class ResultStatus {


    private ResultStatusE status;
    private String message;

    public ResultStatus(ResultStatusE status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultStatusE getStatus() {
        return status;
    }

    public void setStatus(ResultStatusE status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
