package com.example.blog.payloads;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	
	@NotNull
	@Size(min = 4,message = "Username must be min of 4 characters !!")
	private String name;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotNull
	@Size(min = 3,max = 10,message = "Password must be min of 3 chats and max of 10 chars !")
	private String password;
	
	@NotNull
	@Size(min = 3,max = 10,message = "Abount should be min of 3 chars")
	private String about;
}
