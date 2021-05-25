package com.lantin.springboot.config;

import com.lantin.springboot.config.property.ThreadPoolProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lantin
 */

@Configuration
@EnableConfigurationProperties(ThreadPoolProperty.class)
public class ThreadPoolConfig {

    // private static String name;
    //
    // private static int core_pool_size;
    //
    // private static int maxPoolSize;
    //
    // private static int keepAliveSeconds;
    //
    // private static int queueCapacity;
    //
    // private static String threadNamePrefix;
    @Autowired
    ThreadPoolProperty threadPoolProperty;

    @Bean("processThread")
    public ThreadPoolTaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperty.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperty.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolProperty.getKeepAliveSeconds());
        executor.setQueueCapacity(threadPoolProperty.getQueueCapacity());
        executor.setThreadNamePrefix(threadPoolProperty.getThreadNamePrefix());
        // executor.setAllowCoreThreadTimeOut();
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //初始化
        executor.initialize();

        return executor;
    }

}
