package com.delivery.trabalhoDelivery;  // Use o pacote correto do seu projeto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Permite requisições do front-end no localhost:3000
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // URL do seu front-end React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");  // Permite todos os cabeçalhos
            }
        };
    }
}
