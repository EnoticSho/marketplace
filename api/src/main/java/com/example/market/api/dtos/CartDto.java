package com.example.market.api.dtos;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItemList;
    private BigDecimal totalCost;

    public List<CartItemDto> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDto> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
