package com.example.market.cart.integrations;

import com.example.market.api.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ProductServiceIntegration {

    private final RestTemplate restTemplate;
    @Value("${url}")
    private String url;

    @Autowired
    public ProductServiceIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(url + id, ProductDto.class));
    }
}
