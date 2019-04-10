package com.kxl.config;

import com.kxl.filter.AuthInterceptorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptorAdapter()).addPathPatterns("/**")
        .excludePathPatterns("/configuration/security","/swagger-resources","/v2/api-docs","/configuration/ui");
        super.addInterceptors(registry);
    }
}
