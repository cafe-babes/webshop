package com.training360.cafebabeswebshop.user;

public class ResultStatus {

    ResultStatusEnum resultStatusEnum;
    private String message;

    public ResultStatus(ResultStatusEnum resultStatusEnum, String message) {
        this.resultStatusEnum = resultStatusEnum;
        this.message = message;
    }

    public ResultStatusEnum getResultStatusEnum() {
        return resultStatusEnum;
    }

    public String getMessage() {
        return message;
    }
}
