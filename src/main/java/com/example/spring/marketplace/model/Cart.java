package com.example.spring.marketplace.model;

import com.example.spring.marketplace.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItemList;
    private int totalCost;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
         return Collections.unmodifiableList(cartItemList);
    }

    public void addItemToCart(Product product) {
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
        totalCost += cartItem.getTotalPrice();
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
        totalCost = 0;
    }

    public void removeCartItem(Long id) {
        if (cartItemList.removeIf(cartItem -> cartItem.getId().equals(id))) {
            recalculate();
        }
    }

    private void recalculate() {
        totalCost = 0;
        for (CartItem cartItem : cartItemList) {
            totalCost += cartItem.getTotalPrice();
        }
    }

    public int getTotalCost() {
        return totalCost;
    }
}
