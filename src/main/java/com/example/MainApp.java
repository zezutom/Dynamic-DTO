package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp extends SpringBootServletInitializer {

	@Bean
	AppConf appConf() {
		return new AppConf();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
