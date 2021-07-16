package com.lantin.spring.config;

import com.lantin.spring.interceptor.AuthorizationInterceptor;
import com.lantin.spring.interceptor.DemoInterceptor;
import com.lantin.spring.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on 2021/07/07/15:03 周三
 *
 * @author Lantin
 * //
 */
@Configuration
@ComponentScan("com.lantin.spring.interceptor")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    DemoInterceptor demoInterceptor;

    @Bean("myinter")
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(demoInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**");
    }


}
