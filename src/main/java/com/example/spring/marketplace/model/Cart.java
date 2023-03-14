package com.example.spring.marketplace.model;

import com.example.spring.marketplace.entities.Product;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Cart {
    private List<CartItem> cartItemList;
    private int totalCost;

    @PostConstruct
    public void init() {
        cartItemList = new ArrayList<>();
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
}
