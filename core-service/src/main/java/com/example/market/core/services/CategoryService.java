package com.example.market.core.services;

import com.example.market.api.ResourseNotFoundException;
import com.example.market.core.entities.Category;
import com.example.market.core.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() -> new ResourseNotFoundException("Категория не найдена"));
    }
}
