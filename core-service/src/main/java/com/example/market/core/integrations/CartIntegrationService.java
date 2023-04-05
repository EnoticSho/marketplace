package com.example.market.core.integrations;

import com.example.market.api.dtos.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CartIntegrationService {
    private final WebClient cartServiceWebClient;

    @Autowired
    public CartIntegrationService(WebClient cartServiceWebClient) {
        this.cartServiceWebClient = cartServiceWebClient;
    }

    public CartDto getCurrentCart(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.delete()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
