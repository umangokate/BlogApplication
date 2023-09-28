package com.example.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo catRepo;
	
	@Autowired
	ModelMapper model;
	
	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		// TODO Auto-generated method stub
		Category cat = this.model.map(catDto, Category.class);
		Category addCat = this.catRepo.save(cat);
		
		
		return this.model.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer catId) {
		// TODO Auto-generated method stub
		Category old = this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",catId));
		old.setCategoryTitle(category.getCategoryTitle());
		old.setCategoryDescription(category.getCategoryDescription());
		
		Category updated = this.catRepo.save(old);
		
		return this.model.map(updated, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catId) {
		// TODO Auto-generated method stub
		
		Category del = this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", catId));
		this.catRepo.delete(del);
		
		

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<Category> cat = this.catRepo.findAll();
		List<CategoryDto> all = new ArrayList();
		cat.forEach((categ)->{
			all.add(this.model.map(categ, CategoryDto.class));
		});
		return all;
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category cat = this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", catId));
		
		return this.model.map(cat, CategoryDto.class);
	}

}
