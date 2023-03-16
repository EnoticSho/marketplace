package com.example.spring.marketplace.converters;

import com.example.spring.marketplace.dtos.ProductDto;
import com.example.spring.marketplace.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDto entityToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        return productDto;
    }
}
