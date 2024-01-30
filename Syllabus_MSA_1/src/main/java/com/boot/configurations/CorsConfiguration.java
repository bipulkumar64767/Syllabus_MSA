package com.boot.configurations;

import com.boot.constants.ConstantMessages;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ConstantMessages.FRONTEND_PORT)
                .allowedMethods(ConstantMessages.METHOD_GET, ConstantMessages.METHOD_POST, ConstantMessages.METHOD_PUT, ConstantMessages.METHOD_DELETE)
                .allowedHeaders("*")
                .allowCredentials(ConstantMessages.VALUE_TRUE);
    }
}
