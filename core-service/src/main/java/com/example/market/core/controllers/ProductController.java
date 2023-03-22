package com.example.market.core.controllers;

import com.example.market.api.ResourseNotFoundException;
import com.example.market.api.dtos.ProductDto;
import com.example.market.core.converters.ProductConverter;
import com.example.market.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return productConverter.entityToProductDto(productService.getProductById(id).orElseThrow(() -> new ResourseNotFoundException("нет такого продукта")));
    }
}
