package com.example.market.api.dtos;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> itemsDtos;
    private Date date;

    public OrderDto(Long id, String username, List<OrderItemDto> itemsDtos, Date date) {
        this.id = id;
        this.username = username;
        this.itemsDtos = itemsDtos;
        this.date = date;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItemsDtos() {
        return itemsDtos;
    }

    public void setItemsDtos(List<OrderItemDto> itemsDtos) {
        this.itemsDtos = itemsDtos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
