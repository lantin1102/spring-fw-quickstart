package com.lantin.spring.config;

import com.lantin.spring.interceptor.AuthorizationInterceptor;
import com.lantin.spring.interceptor.DemoInterceptor;
import com.lantin.spring.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created on 2021/07/07/15:03 周三
 *
 * @author Lantin
 * //
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    DemoInterceptor demoInterceptor;
    @Autowired
    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(demoInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**");
    }
}
