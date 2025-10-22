package com.example.pt_online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // tắt CSRF để test bằng Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // cho phép /auth/login và /auth/register truy cập tự do
                .anyRequest().authenticated() // các API khác yêu cầu đăng nhập
            );

        return http.build();
    }
}
