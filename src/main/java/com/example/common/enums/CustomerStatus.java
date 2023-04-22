package com.example.common.enums;

public enum CustomerStatus {
    ACTIVE("A"),
    INACTIVE("I");

    private String code;

    CustomerStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
