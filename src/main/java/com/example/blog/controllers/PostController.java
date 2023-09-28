package com.example.blog.controllers;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.entities.Post;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.services.FileService;
import com.example.blog.services.PostService;

import lombok.Value;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService pservice;
	//create
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/users/{userid}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post,@PathVariable("userid") Integer uid,@PathVariable("categoryId") Integer catid)
	{
		PostDto p = this.pservice.createPost(post, catid, uid);
		
		return new ResponseEntity<PostDto>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer uid)
	{
		List<PostDto> p = this.pservice.getAllPostByUser(uid);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("catId") Integer catId)
	{
		List<PostDto> pdt = this.pservice.getPostByCategory(catId);
		return ResponseEntity.ok(pdt);
	}
	
	@GetMapping("/posts/{pid}")
	ResponseEntity<PostDto> getPosts(@PathVariable("pid") Integer pid)
	{
		PostDto p = this.pservice.getPost(pid);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/posts")
	ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue= "0") Integer pageNumber,@RequestParam(value="pageSize",defaultValue = "5") Integer pageSize,@RequestParam(value="sortBy",defaultValue="postId",required=false) String sortBy)
	{
		
		PostResponse p = this.pservice.getAllPost(pageNumber,pageSize,sortBy);
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/posts/{pid}")
	ResponseEntity<ApiResponse> deletePost(@PathVariable("pid") Integer pid)
	{
		this.pservice.deletePost(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("The Post is successfully deleted",true),HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	ResponseEntity<List<PostDto>> getPosts(@PathVariable("keyword") String keyword)
	{
		List<PostDto> post = this.pservice.search(keyword);
		return ResponseEntity.ok(post);
		
	}

	@PostMapping("/posts/image/upload/{postid}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile file,@PathVariable("postid") Integer postId)
	{
		String path = "images/";
		String name = null;
		try {
			name = this.fileService.uploadImage(path, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PostDto p = this.pservice.getPost(postId);
		p.setImage(name);
		
		return ResponseEntity.ok(p);
	}
}
