package com.example.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class MovieRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommenderApplication.class, args);
		System.out.println("Hi");
	}

	 @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Movie Recommendation API")
	                        .version("1.0")
	                        .description("API documentation for the Movie Recommendation System.")
	                        .termsOfService("http://example.com/terms")
	                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	    }
}
