package com.example.Lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentingRest {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "hello";
	}
}