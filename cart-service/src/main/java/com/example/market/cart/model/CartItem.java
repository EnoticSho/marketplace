package com.example.market.cart.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private final Long id;
    private final String title;
    private int quantity;
    private final BigDecimal pricePerCount;
    private BigDecimal totalPrice;

    public CartItem(long id, String title, int quantity, BigDecimal pricePerCount) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.pricePerCount = pricePerCount;
        totalPrice = pricePerCount.multiply(BigDecimal.valueOf(quantity));
    }

    public void changeQuantity(int inc) {
        quantity = quantity + inc;
        totalPrice = pricePerCount.multiply(BigDecimal.valueOf(quantity));
    }
}
