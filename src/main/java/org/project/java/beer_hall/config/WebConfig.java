package org.project.java.beer_hall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourcePath = "file:/X:/Luigi/VSC/ProgettiPersonali/2-beer_hall/beer_hall/uploads/img/beers/";
        registry.addResourceHandler("/img/beers/**")
                .addResourceLocations(resourcePath);
    }
}
