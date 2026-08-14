package com.example.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.CategoryResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           ProductRepository productRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Category createCategory(Category category) {

        category.setStatus(true);

        category.setCreatedAt(java.time.LocalDateTime.now());

        category.setUpdatedAt(java.time.LocalDateTime.now());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category updatedCategory) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setDescription(updatedCategory.getDescription());
        existingCategory.setUpdatedAt(java.time.LocalDateTime.now());

        return categoryRepository.save(existingCategory);
    }

    public Category deactivateCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setStatus(false);
        category.setUpdatedAt(java.time.LocalDateTime.now());

        return categoryRepository.save(category);
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findByStatusTrue();
    }

    public List<CategoryResponse> getCategoryDashboard() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponse(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getDescription(),
                        category.getStatus(),
                        productRepository.countByCategoryCategoryId(
                                category.getCategoryId())
                ))
                .collect(Collectors.toList());
    }
}