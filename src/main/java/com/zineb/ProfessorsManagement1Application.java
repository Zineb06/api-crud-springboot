package com.zineb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "model")
@ComponentScan(basePackages = "controller") 
@ComponentScan(basePackages = "test")
@Configuration
@EnableJpaRepositories("repo")

public class ProfessorsManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProfessorsManagement1Application.class, args);
	}

}
