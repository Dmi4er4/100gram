package by.fmpibsu.stogram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Add Resource Handlers");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/")
                .addResourceLocations("/static/js")
                .addResourceLocations("/static/lib")
                .addResourceLocations("/static/css")
                .addResourceLocations("/static/fonts")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }
}
