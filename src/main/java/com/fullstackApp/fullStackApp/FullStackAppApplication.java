package com.fullstackApp.fullStackApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.fullstackApp.fullStackApp.databases.BudgetBossDataBase.*;

@SpringBootApplication
public class FullStackAppApplication {

	public static void main(String[] args) {
		createTable();
		createDataTable();
		SpringApplication.run(FullStackAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("*");
			}
		};
	}


}
