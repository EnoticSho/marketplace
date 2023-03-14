package com.example.spring.marketplace.controllers;

import com.example.spring.marketplace.model.Cart;
import com.example.spring.marketplace.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCard() {
        return cartService.getCart();
    }

    @PostMapping("/{id}")
    public void addProductToCart(@PathVariable("id") Long id) {
        cartService.addProductToCart(id);
    }

    @DeleteMapping
    public void deleteCart() {
        cartService.clearCart();
    }

    @DeleteMapping("/{id}")
    public void deleteProductFromCart(@PathVariable("id") Long id) {
        cartService.deleteProductFromCart(id);
    }

    @PutMapping("/increment")
    public void changeQuantity(@RequestParam("productId") Long id, @RequestParam("inc") int inc) {
        cartService.editProduct(id, inc);
    }
}
