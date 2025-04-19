package com.example.BM.Books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF'yi devre dışı bırak
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()  // API'ye tüm isteklere izin ver
                        .anyRequest().authenticated() // Diğer her şey için kimlik doğrulama iste
                );
        return http.build();
    }
}