package com.example.ResultEDUService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResultEduServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultEduServiceApplication.class, args);
	}

}
