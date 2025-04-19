package com.example.BM.Books;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm yollar
                .allowedOrigins("*") // Tüm domain'lere izin ver
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"); // Tüm HTTP metotlarına izin
    }
}