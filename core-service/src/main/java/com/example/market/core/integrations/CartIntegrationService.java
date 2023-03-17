package com.example.market.core.integrations;

import com.example.market.api.dtos.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartIntegrationService {
    private final RestTemplate restTemplate;
    @Value("${url}")
    private String url;

    @Autowired
    public CartIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CartDto getCard() {
        return restTemplate.getForObject(url, CartDto.class);
    }
}
