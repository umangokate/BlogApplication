package com.example.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.payloads.CategoryDto;

@Service
public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto catDto);
	CategoryDto updateCategory(CategoryDto category,Integer catId);
	void deleteCategory(Integer catId);
	List<CategoryDto> getAllCategory();
	CategoryDto getCategory(Integer catId);
	
}
