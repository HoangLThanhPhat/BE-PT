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
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để test API bằng Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Cho phép truy cập các API auth
                        .anyRequest().authenticated() // Các API khác cần xác thực
                )
                .formLogin(login -> login.disable()) // Tắt form login mặc định
                .httpBasic(basic -> basic.disable()); // Tắt Basic Auth

        return http.build();
    }
}
