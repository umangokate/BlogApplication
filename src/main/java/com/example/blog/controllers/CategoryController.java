package com.example.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.payloads.UserDto;
import com.example.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> postCategory(@RequestBody CategoryDto catdt)
	{
		CategoryDto cat = this.catService.createCategory(catdt);
		return new ResponseEntity<>(cat,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto catdt,@PathVariable("catId") Integer Id)
	{
		CategoryDto cat = this.catService.updateCategory(catdt,Id);
		return new ResponseEntity<>(cat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("catId") Integer catId)
	{
		this.catService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted successFull",true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategories(@PathVariable("catId") Integer uid)
	{

		return ResponseEntity.ok(this.catService.getCategory(uid));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{

		return ResponseEntity.ok(this.catService.getAllCategory());
	}
}
