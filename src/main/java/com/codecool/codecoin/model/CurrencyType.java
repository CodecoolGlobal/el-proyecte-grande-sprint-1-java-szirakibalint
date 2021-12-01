package com.codecool.codecoin.model;

public enum CurrencyType {
    USD("usd"),
    EUR("eur"),
    HUF("huf");

    private final String value;

    CurrencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
