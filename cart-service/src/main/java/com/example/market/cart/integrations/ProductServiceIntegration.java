package com.example.market.cart.integrations;

import com.example.market.api.ResourseNotFoundException;
import com.example.market.api.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    @Autowired
    public ProductServiceIntegration(WebClient productServiceWebClient) {
        this.productServiceWebClient = productServiceWebClient;
    }

    public ProductDto getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourseNotFoundException(("Товар не найден")))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
