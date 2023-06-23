package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long userId;
	private String userName;
	private String emailId;
	private String password;
	private String gender;
	private String image;
}
