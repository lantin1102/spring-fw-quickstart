package com.lantin.spring.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2021/07/13/16:15 周二
 *
 * @author Lantin
 */
@Configuration
@MapperScan(basePackages = "com.lantin.spring.mapper")
public class MybatisConfig {
}
