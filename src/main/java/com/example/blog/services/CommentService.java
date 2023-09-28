package com.example.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entities.Comment;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repositories.PostRepo;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);

}
