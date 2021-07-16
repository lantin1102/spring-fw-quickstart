package com.lantin.spring.config;

import com.lantin.spring.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/07/16/15:10 周五
 *
 * @author Lantin
 */
@Configuration
public class TestConfig {

    @Value("${lantin.name}")
    private String name;

    @Value("${lantin.sex}")
    private String sex;



}
