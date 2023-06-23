package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	String uplodImage(MultipartFile multipartFile);

}
