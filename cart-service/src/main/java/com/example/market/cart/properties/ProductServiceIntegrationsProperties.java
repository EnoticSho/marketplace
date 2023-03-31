package com.example.market.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductServiceIntegrationsProperties {
    private String url;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
