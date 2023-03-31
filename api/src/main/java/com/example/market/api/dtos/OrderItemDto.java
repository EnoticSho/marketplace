package com.example.market.api.dtos;

public class OrderItemDto {
    private Long id;
    private Long order_id;
    private String productName;
    private int quantity;
    private int cost;

    public OrderItemDto(Long id, Long order_id, String productName, int quantity, int cost) {
        this.id = id;
        this.order_id = order_id;
        this.productName = productName;
        this.quantity = quantity;
        this.cost = cost;
    }

    public OrderItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
