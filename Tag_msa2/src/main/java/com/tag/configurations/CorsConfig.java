package com.tag.configurations;

import com.tag.constants.ConstantMessages;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ConstantMessages.ORIGINS) // Update the port for your frontend application
                .allowedMethods(ConstantMessages.ALLOWED_METHOD_GET, ConstantMessages.ALLOWED_METHOD_POST, ConstantMessages.ALLOWED_METHOD_PUT, ConstantMessages.ALLOWED_METHOD_DELETE)
                .allowedHeaders("*")
                .allowCredentials(ConstantMessages.VALUE_TRUE);
    }
}