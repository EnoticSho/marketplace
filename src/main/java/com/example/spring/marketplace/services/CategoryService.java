package com.example.spring.marketplace.services;

import com.example.spring.marketplace.entities.Category;
import com.example.spring.marketplace.exceptions.ResourseNotFoundException;
import com.example.spring.marketplace.repositories.CategoryRepository;
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
