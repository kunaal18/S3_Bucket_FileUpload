package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private S3Service s3Service;

	@Override
	public UserDto addUser(MultipartFile multipartFile, UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setImage(s3Service.uplodImage(multipartFile));
		userRepository.save(user);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public String deleteUser(Long userId) {
		Optional<User> findById = userRepository.findById(userId);
		userRepository.delete(findById.get());
		return "deleted Successfully";

	}

}
