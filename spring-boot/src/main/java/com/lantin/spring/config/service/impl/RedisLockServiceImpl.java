package com.lantin.spring.config.service.impl;

import com.lantin.spring.config.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/06/23/18:01 周三
 *
 * @author Lantin
 */
@Service
@Slf4j
public class RedisLockServiceImpl implements RedisLockService {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void jobDoing(String uid) {
        String redisKey = String.format("redis.lock.key&%s", uid);
        log.info("service开始执行");
        RLock lock = redissonClient.getLock(redisKey);
        try {
            boolean b = lock.tryLock(0, 4, TimeUnit.SECONDS);
            if (!b) {
                log.error(Thread.currentThread().getName() + "service处理 获取锁" + lock + "失败");
            }
            log.info(Thread.currentThread().getName() + "service处理 获取锁" + lock + "成功");
            Thread.sleep(3500);
            log.info("service 处理完成");
        } catch (InterruptedException e) {

            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
