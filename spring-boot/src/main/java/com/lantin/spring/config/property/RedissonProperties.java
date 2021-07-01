package com.lantin.spring.config.property;


// @Data
// @Component  要想获取这个属性 1.可声明是spring组件 @Component 2.使用@EnableConfigurationProperties 将@ConfigurationProperties的类的Bean注入到spring容器
// @ConfigurationProperties("redisson")
public class RedissonProperties {

    private String address;
    private String password;

}
