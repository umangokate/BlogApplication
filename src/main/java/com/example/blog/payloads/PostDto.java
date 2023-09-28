package com.example.blog.payloads;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.example.blog.entities.Category;
import com.example.blog.entities.Comment;
import com.example.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
private Integer postId;
	
	
	private String title;
	

	private String content;
	private String Image;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<Comment> comments = new ArrayList<>();
}
