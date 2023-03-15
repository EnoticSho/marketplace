package com.example.spring.marketplace.services;

import com.example.spring.marketplace.entities.Product;
import com.example.spring.marketplace.model.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    @Autowired
    public CartService(ProductService productService) {
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
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Не удается добавить продукт с id в корзину: " + id));
        cart.addItemToCart(product);
    }
}
