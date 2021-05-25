package com.lantin.springboot.service.impl;


import com.lantin.springboot.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImpl implements LogService {


    @Async("processThread")
    @Override
    public String appendLog() {
        long l = System.currentTimeMillis();
        log.info("线程池开始处理，正在使用的线程为:{}", Thread.currentThread().getName());
        try {

            Thread.sleep(1000 * 2);

        } catch (InterruptedException e) {
            log.error("处理日志异常", e);
        }
        log.info("线程池开始结束，{}", Thread.currentThread().getName());
        return String.valueOf(l);
    }
}
