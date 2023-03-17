package com.example.market.cart.converter;

import com.example.market.api.dtos.CartItemDto;
import com.example.market.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto modelToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setPricePerCount(cartItem.getPricePerCount());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setTitle(cartItem.getTitle());
        cartItemDto.setTotalPrice(cartItem.getTotalPrice());
        return cartItemDto;
    }
}
