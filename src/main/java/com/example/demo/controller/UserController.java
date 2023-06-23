package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/addUser")
	public ResponseEntity<SuccessResponse> addUser(@RequestPart("image") MultipartFile multipartFile,
			@RequestPart("userDto") String userDto) throws JsonMappingException, JsonProcessingException {

		UserDto readValue = objectMapper.readValue(userDto, UserDto.class);

		return new ResponseEntity<>(new SuccessResponse(false, "added", userService.addUser(multipartFile, readValue)),
				HttpStatus.OK);

	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<SuccessResponse> deleteUser(Long userId) {
		String deleteUser = userService.deleteUser(userId);
		return new ResponseEntity<>(new SuccessResponse(false, "Deleted", deleteUser), HttpStatus.OK);

	}
}
