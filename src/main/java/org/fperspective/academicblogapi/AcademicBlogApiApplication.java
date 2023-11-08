package org.fperspective.academicblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
// @SpringBootApplication(exclude = {
//   MongoAutoConfiguration.class, 
//   MongoDataAutoConfiguration.class
// })
@EnableMongoRepositories(basePackages = "org.fperspective.academicblogapi.repository")
@PropertySource("classpath:application.properties")
// @Profile("FPerspecTive")
public class AcademicBlogApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AcademicBlogApiApplication.class, args);
	}

}
