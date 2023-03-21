package com.example.market.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.products-service")
@Data
public class ProductServiceIntegrationsProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;

    public ProductServiceIntegrationsProperties(String url, Integer connectTimeout, Integer readTimeout, Integer writeTimeout) {
        this.url = url;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }
}
