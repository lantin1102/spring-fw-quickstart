package com.lantin.springboot.service.impl;


import com.lantin.springboot.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImpl2 implements LogService {

    @Autowired
    @Qualifier("processThread")
    ThreadPoolTaskExecutor executor;

    @Override
    public String appendLog() {
        long l = System.currentTimeMillis();
        executor.execute(() -> {
            log.info("线程池开始处理22，正在使用的线程为:{}", Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis());
            try {

                Thread.sleep(1000 * 2);

            } catch (InterruptedException e) {
                log.error("处理日志异常", e);
            }
            log.info("线程池处理结束22，{}", Thread.currentThread().getName());
        });
        return String.valueOf(l);

    }
}
