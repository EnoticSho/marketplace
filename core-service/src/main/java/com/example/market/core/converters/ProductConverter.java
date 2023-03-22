package com.example.market.core.converters;

import com.example.market.api.dtos.ProductDto;
import com.example.market.core.entities.Product;
import com.example.market.core.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    private final CategoryService categoryService;

    public ProductConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductDto entityToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        return productDto;
    }

    public Product productDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getTitle()));
        return product;
    }
}
