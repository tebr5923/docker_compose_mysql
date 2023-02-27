package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("auth/login");
        registry.addViewController("/auth/login").setViewName("auth/login");
        registry.addViewController("/user").setViewName("users/show");
        registry.addViewController("/admin").setViewName("admin/admin");
    }

}
