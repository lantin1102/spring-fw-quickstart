package com.lantin.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lantin
 */

@Configuration
@ConfigurationProperties(prefix = "custom.thread-pool")
public class ThreadPoolConfig {

    private static int corePoolSize;

    private static int maxPoolSize;

    private static int keepAliveSeconds;

    private static int queueCapacity;

    private static String threadNamePrefix;

    @Bean("processThread")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        // executor.setAllowCoreThreadTimeOut();
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //初始化
        executor.initialize();

        return executor;
    }

}
