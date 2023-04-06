package com.example.market.api.dtos;

public class StringResponse {
    private String value;

    public StringResponse() {
    }

    public StringResponse(String string) {
        this.value = string;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
