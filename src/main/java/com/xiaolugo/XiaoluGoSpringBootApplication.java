package com.xiaolugo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class XiaoluGoSpringBootApplication {

	@RequestMapping(value = "/")
	String home(){
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(XiaoluGoSpringBootApplication.class, args);
	}
}
