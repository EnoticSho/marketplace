package com.example.market.cart.services;

import com.example.market.api.dtos.ProductDto;
import com.example.market.cart.integrations.ProductServiceIntegration;
import com.example.market.cart.model.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class CartService {
    private Map<String, Cart> carts;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    @Autowired
    public CartService(ProductServiceIntegration productServiceIntegration, RedisTemplate<String, Object> redisTemplate) {
        this.productServiceIntegration = productServiceIntegration;
        this.redisTemplate = redisTemplate;
    }

    public Cart getCart(String uuid) {
        System.out.println(redisTemplate);
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void clearCart(String uuid) {
        execute(uuid, Cart::delete);
    }

    public void deleteProductFromCart(Long id, String uuid) {
        execute(uuid, cart -> cart.removeCartItem(id));
    }

    public void editProduct(Long id, int inc, String uuid) {
        execute(uuid, cart -> cart.editCartItem(id, inc));
    }

    public void addProductToCart(Long id, String uuid) {
        ProductDto product = productServiceIntegration.getProductById(id);
        execute(uuid, cart -> cart.addItemToCart(product));
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = carts.get(uuid);
        System.out.println(carts.get(uuid));
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }
}
