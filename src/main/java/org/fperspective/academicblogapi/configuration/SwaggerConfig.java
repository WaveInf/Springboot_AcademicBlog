package org.fperspective.academicblogapi.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@ComponentScan({"main.controller", "main.repository", "main.service", "main.configuration"})
@OpenAPIDefinition(
    
    info = @Info(
        contact = @Contact(
            name = "Wave",
            email = "annpse172989@fpt.edu.vn",
            url = "https://fperspective-server.onrender.com"
        ),
        description = "OpenAPI Swagger",
        title = "Wave's OpenAPI",
        version = "1.0"
    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:8080"
        ),
        @Server(
            description = "PROD ENV",
            url = "https://fperspective-server.onrender.com"
        )
    }
)
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("controller-api")
                .packagesToScan("org.fperspective.academicblogapi.controller") // Specify the package to scan
                .build();
    }
}
