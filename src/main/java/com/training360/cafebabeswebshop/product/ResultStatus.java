package com.training360.cafebabeswebshop.product;

public class ResultStatus {
    private boolean ok;
    private String message;

    public ResultStatus(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
