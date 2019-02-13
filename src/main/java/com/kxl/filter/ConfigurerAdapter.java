package com.kxl.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2019/2/13.
 */
@Configuration
@Component
public class ConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Bean
    AuthInterceptorAdapter authInterceptorAdapter() {
        return new AuthInterceptorAdapter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptorAdapter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
