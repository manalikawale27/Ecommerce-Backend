package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CategoryResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService=categoryService;
	}
	
	@PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
	
	@GetMapping
	public List<Category> getAllCategories() {
	    return categoryService.getAllCategories();
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(
	        @PathVariable Long id,
	        @RequestBody Category category) {

	    return categoryService.updateCategory(id, category);
	}
	
	
	@DeleteMapping("/{id}")
	public Category deactivateCategory(@PathVariable Long id) {
	    return categoryService.deactivateCategory(id);
	}

	
	@GetMapping("/active")
	public List<Category> getActiveCategories() {
	    return categoryService.getActiveCategories();
	}
	
	@GetMapping("/dashboard")
	public List<CategoryResponse> getCategoryDashboard() {
	    return categoryService.getCategoryDashboard();
	}
	
}
