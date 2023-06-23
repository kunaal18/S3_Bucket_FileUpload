package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ConfigS3 {

//	private final String accessKeyId = "AKIAWVZUI5PZUGTA3Q7E";
//	private final String secretKey = "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";
//	private final String region = "ap-south-1";

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String accessSecret;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public S3Client s3Client() {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, accessSecret);
		return S3Client.builder().region(Region.of(region))
				.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
	}
}
