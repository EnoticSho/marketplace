package com.example.market.cart.model;

import com.example.market.api.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItemList;
    private BigDecimal totalCost;

    public Cart() {
        cartItemList = new ArrayList<>();
        totalCost = new BigDecimal(0);
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void addItemToCart(ProductDto product) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId().equals(product.getId())) {
                cartItem.changeQuantity(1);
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem(product.getId(),
                product.getTitle(),
                1,
                product.getPrice());
        cartItemList.add(cartItem);
        totalCost = totalCost.add(cartItem.getTotalPrice());
    }

    public void editCartItem(Long id, int inc) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId().equals(id)) {
                cartItem.changeQuantity(inc);
                if (cartItem.getQuantity() == 0) {
                    cartItemList.remove(cartItem);
                    recalculate();
                    break;
                }
                recalculate();
            }
        }
    }

    public void delete() {
        cartItemList.clear();
        totalCost = BigDecimal.ZERO;
    }

    public void removeCartItem(Long id) {
        if (cartItemList.removeIf(cartItem -> cartItem.getId().equals(id))) {
            recalculate();
        }
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemList) {
            totalCost = totalCost.add(cartItem.getTotalPrice());
        }
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
