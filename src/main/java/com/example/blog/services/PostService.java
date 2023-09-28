package com.example.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.entities.Post;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;

@Service
public interface PostService {

	//create
	PostDto createPost(PostDto postDto,Integer cid,Integer uid);
	
	//update
	PostDto updatePost(PostDto postDto,Integer pid);
	
	//delete
	void deletePost(Integer pid);
	
	//getAllPost
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	//getSinglePost
	PostDto getPost(Integer pid);
	
	//getAllpostByCategory
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//getAllPostbyUser
	List<PostDto> getAllPostByUser(Integer userId);
	
	List<PostDto> search(String title);
}
