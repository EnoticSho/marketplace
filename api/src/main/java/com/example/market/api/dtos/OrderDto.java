package com.example.market.api.dtos;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private String user;
    private List<OrderItemDto> itemsDtos;
    private Date date;

    public OrderDto(Long id, String user, List<OrderItemDto> itemsDtos, Date date) {
        this.id = id;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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