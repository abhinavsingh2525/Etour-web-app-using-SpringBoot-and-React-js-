package com.etour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.etour.dto")
@ComponentScan(basePackages="com.*")
@EntityScan(basePackages="com.*")
@EnableJpaRepositories(basePackages="com.*")
public class EtourProject4Application {

	public static void main(String[] args) {
		SpringApplication.run(EtourProject4Application.class, args);
	}

	 @Bean
	  public WebMvcConfigurer corsConfigurer() 
	 {
	    return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**")
	                .allowedOrigins("http://localhost:3000")
	                .allowedMethods("GET", "POST", "PUT", "DELETE")
	                .allowedHeaders("*")
	                .allowCredentials(true)
	                .maxAge(3600);
	      }
	    };
	  }
	
	
}
