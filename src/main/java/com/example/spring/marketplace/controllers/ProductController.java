package com.example.spring.marketplace.controllers;

import com.example.spring.marketplace.converters.ProductConverter;
import com.example.spring.marketplace.dtos.ProductDto;
import com.example.spring.marketplace.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @Autowired
    public ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts().stream().map(productConverter::entityToProductDto).toList();
    }
}
