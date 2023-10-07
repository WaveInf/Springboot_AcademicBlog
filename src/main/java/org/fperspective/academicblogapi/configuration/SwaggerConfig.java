package org.fperspective.academicblogapi.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
    
    info = @Info(
        contact = @Contact(
            name = "Wave",
            email = "annpse172989@fpt.edu.vn",
            url = "https://github.com/WaveInf"
        ),
        description = "OpenAPI Swagger",
        title = "Wave's OpenAPI",
        version = "1.0"
    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:8080/api/v1"
        ),
        @Server(
            description = "PROD ENV",
            url = "http://localhost:8080/api/v1/blog"
        )
    }
)
// @SecurityScheme(
//     name = "bearerAuth",
//     description = "JWT auth description",
//     scheme = "bearer",
//     type = SecuritySchemeType.HTTP,
//     bearerFormat = "JWT",
//     in = SecuritySchemeIn.HEADER
// )
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("controller-api")
                .packagesToScan("org.fperspective.academicblogapi.controller") // Specify the package to scan
                .build();
    }
}
