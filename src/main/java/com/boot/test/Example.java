package com.boot.test;

import com.boot.filter.AuthFilter;
import com.boot.filter.ValidateTokenFilter;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@ServletComponentScan
public class Example {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
    @Bean
    public FilterRegistrationBean MyFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("authFilter");
        registration.setOrder(1);

        return registration;
    }
    @Bean
    @Order(2)
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ValidateTokenFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("validateTokenFilter");
        return registration;
    }


}
