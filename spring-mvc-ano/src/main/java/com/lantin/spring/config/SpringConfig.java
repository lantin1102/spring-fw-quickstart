package com.lantin.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2021/07/17/10:39 周六
 *
 * @author Lantin
 */
@Configuration
@PropertySource(value = "classpath:property/jdbc.properties") //不允许通配
@Import(JdbcConfig.class)
@ComponentScan
public class SpringConfig {
}
