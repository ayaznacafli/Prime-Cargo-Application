package com.prime.model;

public enum CurrencyType {
    TL("TL"),
    AZN("AZN"),
    RUB("RUB");

    private String value;

    CurrencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
