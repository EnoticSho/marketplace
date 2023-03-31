package com.example.market.cart.services;

import com.example.market.api.dtos.ProductDto;
import com.example.market.cart.integrations.ProductServiceIntegration;
import com.example.market.cart.model.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private Cart cart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    @Autowired
    public CartService(ProductServiceIntegration productServiceIntegration) {
        this.productServiceIntegration = productServiceIntegration;
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        cart.delete();
    }

    public void deleteProductFromCart(Long id) {
        cart.removeCartItem(id);
    }

    public void editProduct(Long id, int inc) {
        cart.editCartItem(id, inc);
    }

    public void addProductToCart(Long id) {
        ProductDto product = productServiceIntegration.getProductById(id);
        cart.addItemToCart(product);
    }
}
