package com.example.market.cart.converter;

import com.example.market.api.dtos.CartDto;
import com.example.market.cart.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;
    public CartDto EntityToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(cart.getTotalCost());
        cartDto.setCartItemList(cart.getCartItemList().stream().map(cartItemConverter::modelToCartItemDto).toList());
        return cartDto;
    }
}
