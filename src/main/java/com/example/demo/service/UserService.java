package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDto;

public interface UserService {
	UserDto addUser(MultipartFile multipartFile, UserDto userDto);

	String deleteUser(Long userId);
}
