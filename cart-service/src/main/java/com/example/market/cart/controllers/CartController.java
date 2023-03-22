package com.example.market.cart.controllers;

import com.example.market.api.dtos.CartDto;
import com.example.market.cart.converter.CartConverter;
import com.example.market.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @Autowired
    public CartController(CartService cartService, CartConverter cartConverter) {
        this.cartService = cartService;
        this.cartConverter = cartConverter;
    }

    @GetMapping
    public CartDto getCard() {
        return cartConverter.EntityToCartDto(cartService.getCart());
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
    public void changeQuantity(@RequestParam("productId") Long id,
                               @RequestParam("inc") int inc) {
        cartService.editProduct(id, inc);
    }
}
