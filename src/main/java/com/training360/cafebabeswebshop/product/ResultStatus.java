package com.training360.cafebabeswebshop.product;

public class ResultStatus {

    private ResultStatusEnum resultStatusEnum;
    private String message;

    public ResultStatus(ResultStatusEnum resultStatusEnum, String message) {
        this.resultStatusEnum = resultStatusEnum;
        this.message = message;
    }

    public ResultStatusEnum getStatus() {
        return resultStatusEnum;
    }

    public void setStatus(ResultStatusEnum resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
