package com.example.spring.marketplace.model;

import lombok.Data;

@Data
public class CartItem {
    private final Long id;
    private final String title;
    private int quantity;
    private final int pricePerCount;
    private int totalPrice;

    public CartItem(long id, String title, int quantity, int pricePerCount) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.pricePerCount = pricePerCount;
        totalPrice = quantity * pricePerCount;
    }


    public void changeQuantity(int inc) {
        quantity = quantity + inc;
        totalPrice = quantity * pricePerCount;
    }
}
