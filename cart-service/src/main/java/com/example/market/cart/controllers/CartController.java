package com.example.market.cart.controllers;

import com.example.market.api.dtos.CartDto;
import com.example.market.api.dtos.StringResponse;
import com.example.market.cart.converter.CartConverter;
import com.example.market.cart.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @Autowired
    public CartController(CartService cartService, CartConverter cartConverter) {
        this.cartService = cartService;
        this.cartConverter = cartConverter;
    }

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}")
    public CartDto getCard(@RequestHeader(name = "username", required = false) String username,
                           @PathVariable String uuid) {
        String targetUuid = getCartUuid(uuid, username);
        return cartConverter.EntityToCartDto(cartService.getCart(targetUuid));
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addProductToCart(@RequestHeader(name = "username", required = false) String username,
                                 @PathVariable String uuid,
                                 @PathVariable("id") Long id) {
        String targetUuid = getCartUuid(uuid, username);
        cartService.addProductToCart(id, targetUuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteCart(@RequestHeader(name = "username", required = false) String username,
                           @PathVariable String uuid) {
        String targetUuid = getCartUuid(uuid, username);
        cartService.clearCart(targetUuid);
    }

    @DeleteMapping("/{uuid}/{id}")
    public void deleteProductFromCart(@RequestHeader(name = "username", required = false) String username,
                                      @PathVariable String uuid,
                                      @PathVariable("id") Long id) {
        String targetUuid = getCartUuid(uuid, username);
        cartService.deleteProductFromCart(id, targetUuid);
    }

    @PutMapping("/{uuid}/increment")
    public void changeQuantity(@RequestHeader(name = "username", required = false) String username,
                               @PathVariable String uuid,
                               @RequestParam("productId") Long id,
                               @RequestParam("inc") int inc) {
        String targetUuid = getCartUuid(uuid, username);
        cartService.editProduct(id, inc, targetUuid);
    }

    private String getCartUuid(String uuid, String username) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
