package com.lantin.spring.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

/**
 * Created on 2021/06/22/16:54 周二
 *
 * @author Lantin
 */
@Data
@Configuration
@ConfigurationProperties("redisson")
public class RedissonConfig {

    private String address;
    private String password;
    /**
     * 一个请求流程完成的超时时间
     */
    private int timeout;
    /**
     * 建立连接超时时间
     */
    private int connectTimeout;
    private int database;

    @Bean
    public RedissonClient client() throws IOException {
        // Config config = new Config();
        // config.useSingleServer()
        //         .setAddress(address)
        //         .setPassword(password)
        //         .setTimeout(timeout)
        //         .setDatabase(database)
        //         .setConnectTimeout(connectTimeout);
        //  也可以使用读取外部位置文件的方式
        URL resource = RedissonConfig.class.getClassLoader().getResource("config/redisson-config.yml");
        Config config = Config.fromYAML(resource);
        return Redisson.create(config);
    }

    //  导出默认配置
    // public static void main(String[] args) throws IOException {
    //
    //     Config config = new Config();
    //     config.useSingleServer();
    //     String yamlFormat = config.toYAML();
    //     System.out.println(yamlFormat);
    // }
}
