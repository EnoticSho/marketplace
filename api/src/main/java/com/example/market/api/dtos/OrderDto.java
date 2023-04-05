package com.example.market.api.dtos;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> itemsDto;
    private Date date;

    public OrderDto(Long id, String username, List<OrderItemDto> itemsDto, Date date) {
        this.id = id;
        this.username = username;
        this.itemsDto = itemsDto;
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

    public List<OrderItemDto> getItemsDto() {
        return itemsDto;
    }

    public void setItemsDto(List<OrderItemDto> itemsDto) {
        this.itemsDto = itemsDto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
