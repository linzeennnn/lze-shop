package com.lze_shop.lze_shop.common.classes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cros {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")  // 允许所有来源
                        .allowedMethods("*")         // 允许所有方法 GET, POST, etc.
                        .allowedHeaders("*")         // 允许所有请求头
                        .allowCredentials(false)     // 是否允许 Cookie
                        .maxAge(3600);
            }
        };
    }
}

