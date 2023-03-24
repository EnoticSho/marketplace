package com.example.market.core.controllers;

import com.example.market.api.ResourseNotFoundException;
import com.example.market.api.dtos.ProductDto;
import com.example.market.core.converters.ProductConverter;
import com.example.market.core.entities.Product;
import com.example.market.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public List<ProductDto> findAllProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        Specification<Product> specification = productService.createSpecByFilter(maxPrice, minPrice, title);
        return productService
                .findAllProducts(specification, page - 1)
                .map(productConverter::entityToProductDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return productConverter.entityToProductDto(productService.getProductById(id).orElseThrow(() -> new ResourseNotFoundException("нет такого продукта")));
    }
}
