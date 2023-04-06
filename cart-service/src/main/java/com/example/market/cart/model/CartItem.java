package com.example.market.cart.model;

import java.math.BigDecimal;

public class CartItem {
    private Long id;
    private String title;
    private int quantity;
    private BigDecimal pricePerCount;
    private BigDecimal totalPrice;

    public CartItem() {
    }

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

    public CartItem(Long id, String title, int quantity, BigDecimal pricePerCount, BigDecimal totalPrice) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.pricePerCount = pricePerCount;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerCount() {
        return pricePerCount;
    }

    public void setPricePerCount(BigDecimal pricePerCount) {
        this.pricePerCount = pricePerCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
