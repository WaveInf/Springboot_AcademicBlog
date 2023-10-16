package org.fperspective.academicblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class AcademicBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicBlogApiApplication.class, args);
	}

}
