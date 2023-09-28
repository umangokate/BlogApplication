package com.example.blog.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

	String message;
	boolean success;
}
