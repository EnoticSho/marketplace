package com.example.market.core.services;

import com.example.market.api.ResourseNotFoundException;
import com.example.market.core.entities.Product;
import com.example.market.core.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void findAllProducts() {

    }

    @Test
    void getProductById() {
        Product product = productService.getProductById(1L).orElseThrow(() -> new ResourseNotFoundException(""));
        Assertions.assertNotNull(product);
        Assertions.assertNotNull(product.getId());
    }
}