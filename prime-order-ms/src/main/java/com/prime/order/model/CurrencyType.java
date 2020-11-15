package com.prime.order.model;

public enum CurrencyType {
    TL("TL"),
    AZN("AZN"),
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");


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
