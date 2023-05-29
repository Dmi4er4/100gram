package by.fmpibsu.stogram;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/")
                .addResourceLocations("/static/js")
                .addResourceLocations("/static/lib")
                .addResourceLocations("/static/css")
                .addResourceLocations("/static/fonts")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }
}