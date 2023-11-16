package org.fperspective.academicblogapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${FRONT_END_URL:default}")
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String test = frontendUrl + "/login";
        registry.addMapping("/**")
                .allowedOrigins(frontendUrl, test) // your reactjs URL
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization") // Adjust headers you need to allow
                .allowCredentials(true); // Add only if you want to access cookie
    }
}
