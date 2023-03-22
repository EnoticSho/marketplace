package com.example.market.api.dtos;

import java.util.List;

public class CategoryDto {
    private Long id;
    private String title;
    private List<ProductDto> productDtos;

    public CategoryDto(Long id, String title, List<ProductDto> productDtos) {
        this.id = id;
        this.title = title;
        this.productDtos = productDtos;
    }

    public CategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
