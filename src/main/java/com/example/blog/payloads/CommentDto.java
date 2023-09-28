package com.example.blog.payloads;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.blog.entities.Post;

public class CommentDto {

	private Integer id;
	
	private String content;

}
