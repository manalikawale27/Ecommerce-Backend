package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository=productRepository;
		this.categoryRepository=categoryRepository;
	}
	
	 public Product createProduct(Product product, Long categoryId) {

	        Category category = categoryRepository.findById(categoryId)
	                .orElseThrow(() -> new RuntimeException("Category not found"));

	        product.setCategory(category);

	        return productRepository.save(product);
	    }
	 
	 public long getProductCountByCategory(Long categoryId) {
		    return productRepository.countByCategoryCategoryId(categoryId);
		}
	 
	 
	
	
	
	

}
