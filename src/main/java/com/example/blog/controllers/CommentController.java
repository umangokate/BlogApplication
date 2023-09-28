package com.example.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.repositories.CommentRepo;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

	@Autowired
	CommentRepo comrepo;
	

}
