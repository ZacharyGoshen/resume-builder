package com.zachgoshen.resumebuilder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public CommandLineRunner startup(ResumeRepository repository) {
		return (args) -> {
			repository.save(
				new Resume(
					"Zachary",
					"Goshen",
					"917 S Rolfe St",
					"Arlington",
					"VA",
					"4434041716",
					"zachgoshen@gmail.com"
				)
			);
		};
	}
}
