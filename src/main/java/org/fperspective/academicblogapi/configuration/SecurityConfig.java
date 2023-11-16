package org.fperspective.academicblogapi.configuration;

import java.util.List;

import org.fperspective.academicblogapi.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc
@ComponentScan({"main.controller", "main.repository", "main.service", "main.configuration"})
public class SecurityConfig {

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    // @Autowired
    // private CorsFilter corsFilter;

    @Value("${FRONT_END_URL:default}")
    private String frontendUrl;

    @Bean
    // @Order(1) 
    //Add authentication (Google, Github, username/password) , CredentialService userCredentialService
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource());
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionConcurrency((concur) -> {
                        concur.maximumSessions(1).expiredUrl("/login?expired");
                    });
                })
                .authorizeHttpRequests(author -> {
                author.requestMatchers("/").permitAll();                
                author.anyRequest().authenticated();
            })
                .oauth2Login(oc -> {
                    // oc.userInfoEndpoint(ui -> ui.userService(authService.oauth2LoginHandler()));
                    oc.failureHandler(new SimpleUrlAuthenticationFailureHandler(frontendUrl + "/login"));
                    oc.successHandler(oAuth2LoginSuccessHandler);
                    
                    // oc.defaultSuccessUrl(frontendUrl);
                    // oc.failureUrl(frontendUrl+"/login");
                })
                // .oauth2ResourceServer(oauth2 -> {
                //     oauth2.bearerTokenResolver(bearerTokenResolver());
                // })
                // .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //Allow CORS for all HTTPMethod 
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(frontendUrl));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.OPTIONS);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successLogger() {
        return event -> {
            log.info("success: {}", event.getAuthentication());
        };
    }
}
