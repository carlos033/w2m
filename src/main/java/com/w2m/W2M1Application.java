package com.w2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class W2M1Application{

	public static void main(String[] args) {
		SpringApplication.run(W2M1Application.class, args);
	}

}
