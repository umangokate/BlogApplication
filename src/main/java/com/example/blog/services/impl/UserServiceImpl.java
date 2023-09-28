package com.example.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.exceptions.*;
import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepo userRepo;

	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub\
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return null;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updateUser = this.userRepo.save(user);
		UserDto user1 = this.UserToDto(updateUser);
		return user1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> allUser = this.userRepo.findAll();
		
		List<UserDto> userList = allUser.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
		
		
		return userList;
	}
	
	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
		//Converting DTO to USER using model mapper
		User user =  this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	
	private UserDto UserToDto(User user)
	{
		UserDto userDto =  this.modelMapper.map(user, UserDto.class);
	
		return userDto;
	}

}
