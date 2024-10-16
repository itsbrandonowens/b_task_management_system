package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable authentication for all requests
        http
            .csrf().disable()  // Disable CSRF protection (not recommended for production)
            .authorizeRequests()
            .anyRequest().permitAll(); // Allow all requests without authentication
        return http.build();
    }
}
