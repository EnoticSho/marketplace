package com.example.market.api.dtos;

public class CartItemDto {
    private Long id;
    private String title;
    private int quantity;
    private int pricePerCount;
    private int totalPrice;

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

    public int getPricePerCount() {
        return pricePerCount;
    }

    public void setPricePerCount(int pricePerCount) {
        this.pricePerCount = pricePerCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
