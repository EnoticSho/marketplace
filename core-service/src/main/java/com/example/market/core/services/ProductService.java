package com.example.market.core.services;

import com.example.market.core.repositories.ProductRepository;
import com.example.market.core.entities.Product;
import com.example.market.core.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAllProducts(Specification<Product> specification, int page) {
        return productRepository.findAll(specification, PageRequest.of(page, 5));
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Specification<Product> createSpecByFilter(Integer maxPrice, Integer minPrice, String title) {
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (title != null) {
            specification = specification.and(ProductSpecifications.titleLike(title));
        }
        return specification;
    }
}
