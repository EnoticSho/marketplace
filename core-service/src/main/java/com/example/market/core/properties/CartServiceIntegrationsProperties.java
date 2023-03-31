package com.example.market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationsProperties {
    private String url;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
