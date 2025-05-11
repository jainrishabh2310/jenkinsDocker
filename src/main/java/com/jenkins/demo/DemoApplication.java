package com.jenkins.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	@RequestMapping("/api")
	public String api() {
		return "Docker With Jenkins runs Success";
	}

	@RequestMapping("/post")
	public String api() {
		return "Docker With Jenkins runs Success using post API";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
