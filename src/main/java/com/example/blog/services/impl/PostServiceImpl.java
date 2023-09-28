package com.example.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postrep;
	
	@Autowired
	CategoryRepo catRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer cid,Integer uid) {
		
		User us = this.userRepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","id",uid));
		
		Category cat = this.catRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("Category","id",cid));
		
		// TODO Auto-generated method stub
		Post p = this.mapper.map(postDto, Post.class);
		
		p.setCategory(cat);
		p.setUser(us);
		p.setAddedDate(new Date());
		p.setImage("default.png");
		Post newPost = this.postrep.save(p);
		return this.mapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer pid) {
//		// TODO Auto-generated method stub
		Post post = this.postrep.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Post","Id",pid));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		Post upd = this.postrep.save(post);
		return this.mapper.map(upd, PostDto.class);
	}

	@Override
	public void deletePost(Integer pid) {
		
		Post p = this.postrep.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","id",pid));
		this.postrep.delete(p);
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		// TODO Auto-generated method stub
		
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		Page<Post> pagePost = this.postrep.findAll(page);
		
		List<Post> p = pagePost.getContent();
		
		PostResponse pResp = new PostResponse();
		
		
		List<PostDto> result = new ArrayList<>();
		
		p.forEach((post)->{
	result.add(this.mapper.map(post, PostDto.class));
});
		pResp.setContent(result);
		pResp.setPageNumber(pagePost.getNumber());
		pResp.setPageSize(pagePost.getSize());
		pResp.setTotalPages(pagePost.getTotalPages());
		pResp.setTotalPages(pagePost.getTotalPages());
		
		pResp.setLastPage(pagePost.isLast());
return pResp;

	}

	@Override
	public PostDto getPost(Integer pid) {
		// TODO Auto-generated method stub
		Post p = this.postrep.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","id",pid));
		return this.mapper.map(p, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		List<Post> p = this.postrep.findByCategory(cat);
		List<PostDto> result = new ArrayList<>();
				p.forEach((post)->{
			result.add(this.mapper.map(post, PostDto.class));
		});
		return result;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User us = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Category","category id",userId));
		List<Post> p = this.postrep.findByUser(us);
		List<PostDto> pdt = new ArrayList<>();
		p.forEach((post)->{
			pdt.add(this.mapper.map(post, PostDto.class));
		});
		return pdt;
	}

	@Override
	public List<PostDto> search(String title) {
		// TODO Auto-generated method stub
		List<Post> p = this.postrep.findByTitleContaining(title);
		List<PostDto> pdt = new ArrayList<>();
		p.forEach((post)->{
			pdt.add(this.mapper.map(post, PostDto.class));
		});
		return pdt;

	}
	
	


}
