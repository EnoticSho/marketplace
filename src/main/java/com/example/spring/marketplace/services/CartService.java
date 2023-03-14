package com.example.spring.marketplace.services;

import com.example.spring.marketplace.entities.Product;
import com.example.spring.marketplace.model.Cart;
import com.example.spring.marketplace.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final Cart cart;
    private final ProductService productService;

    @Autowired
    public CartService(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
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
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("error"));
        cart.addItemToCart(product);
    }

    public List<CartItem> findAllCartItems() {
        return cart.getCartItemList();
    }
}
