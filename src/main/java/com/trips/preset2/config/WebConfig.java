package com.trips.preset2.config;

import com.trips.preset2.properties.cors.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // 생성자 만들어 줌. -> 생성자 주입도 포함되는 셈.
public class WebConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders(corsProperties.exposedHeaders())
                .allowedHeaders(corsProperties.allowed().headers())
                .allowedMethods(corsProperties.allowed().methods())
                .allowedOrigins(corsProperties.allowed().origins());
    }
}
