package com.codebattles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.codebattles.ranking.PagingSortingURLParamsInterceptor;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
      interceptorRegistry.addInterceptor(new PagingSortingURLParamsInterceptor());
    }

}