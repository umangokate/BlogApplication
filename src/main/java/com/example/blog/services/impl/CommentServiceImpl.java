package com.example.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.blog.entities.Comment;
import com.example.blog.entities.Post;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repositories.CommentRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.services.CommentService;

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	PostRepo postRepo;

	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		// TODO Auto-generated method stub
		Post p = this.postRepo.findById(postId).orElseThrow();
		
		Comment c = this.mapper.map(commentDto, Comment.class);
		
		c.setPost(p);
		Comment temp = this.commentRepo.save(c);
		
		return this.mapper.map(temp, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		// TODO Auto-generated method stub
		 Comment com = this.commentRepo.findById(commentId).orElseThrow();
		 this.commentRepo.delete(com);

	}

}
