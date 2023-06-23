package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private S3Client s3Client; // we can perform any operation in S3 bucket we can directly upload , download
								// and delete the file using S3Client object

	private final String bucketName = "myfirstbucketkunal";

	@Override
	public String uplodImage(MultipartFile multipartFile) {
		String originalFilename = multipartFile.getOriginalFilename();
		File convertMultiPartFileToFile = convertMultiPartFileToFile(multipartFile);
		PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucketName).key(originalFilename).build();
		s3Client.putObject(objectRequest, RequestBody.fromFile(convertMultiPartFileToFile)); //putObject will help us to uplaod a fle in S3bucket
		convertMultiPartFileToFile.delete();   // once u upload on S3Bucket just delete that file 
		return getPublicImageUrl(originalFilename);
	}

	private File convertMultiPartFileToFile(MultipartFile multipartFile) {
		File convertedFile = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
			fileOutputStream.write(multipartFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedFile;

	}

	private String getPublicImageUrl(String imageName) {
		return "https://" + bucketName + ".s3.amazonaws.com/" + imageName;

	}


}
