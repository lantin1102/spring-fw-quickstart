package com.lantin.springboot.controller;

import com.lantin.springboot.common.CommonResponse;
import com.lantin.springboot.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/06/22/16:52 周二
 *
 * @author Lantin
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {



    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisLockService redisLockService;

    @GetMapping("lock")
    public CommonResponse<Object> tryRedisLock(String uid) {
        String redisKey = String.format("redis.lock.key&%s", uid);
        RLock lock = redissonClient.getLock(redisKey);
        try {
            boolean locked = lock.tryLock(1, 5, TimeUnit.SECONDS);
            log.info(Thread.currentThread().getName() + "获取锁的状态为" + locked);
            if (locked) {
                log.info(Thread.currentThread().getName() + "获取锁" + lock);
                log.info("加锁后进行service处理");
                redisLockService.jobDoing(uid);
                log.info("service处理成功");
                return CommonResponse.success("处理成功");
            }
            return CommonResponse.failure(Thread.currentThread().getName() + "未获取到锁" + lock);
        } catch (InterruptedException e) {
            return CommonResponse.failure("内部错误");
        } finally {
            if (lock.isLocked()) {
                if (lock.isHeldByCurrentThread()) {
                    log.info(Thread.currentThread().getName() + "释放锁" + lock);
                    lock.unlock();
                }
            }
        }
    }
}
