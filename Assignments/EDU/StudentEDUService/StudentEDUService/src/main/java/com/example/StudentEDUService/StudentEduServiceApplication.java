package com.example.StudentEDUService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentEduServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentEduServiceApplication.class, args);
	}

}
