package com.lantin.springboot.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public RedissonClient client() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setPassword(password);

        return Redisson.create(config);
    }

}
