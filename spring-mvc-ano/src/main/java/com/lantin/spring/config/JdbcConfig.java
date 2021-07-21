package com.lantin.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created on 2021/07/17/10:42 周六
 *
 * @author Lantin
 */

public class JdbcConfig {
    @Value("${jdbc.mysql.username}")
    private String username;
    @Value("${jdbc.mysql.password}")
    private String password;
    @Value("${jdbc.mysql.url}")
    private String url;
    @Value("${jdbc.mysql.driver-class-name}")
    private String driverClassName;


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }
}
