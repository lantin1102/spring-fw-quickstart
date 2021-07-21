package com.lantin.spring.controller;

import com.lantin.common.enums.BasicError;
import com.lantin.core.basic.CommonResponse;
import com.lantin.spring.config.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    StringRedisTemplate stringRedisTemplate;

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

    @GetMapping("template")
    public CommonResponse<Object> redisTemplate(String uid) {
        String redisKey = String.format("redis.template.key&%s", uid);
        stringRedisTemplate.opsForValue().set(redisKey, uid);
        return CommonResponse.success();
    }

    @GetMapping("incr/drawNum")
    public CommonResponse<Object> fangLianDian(String activityId, String mid) {

        String key = String.format("redis.fangliandian:%s:%s", activityId, mid);
        RBucket<Object> bucket = redissonClient.getBucket(key);
        boolean flag = bucket.trySet(System.currentTimeMillis(), 1000, TimeUnit.MILLISECONDS);
        if (!flag) {
            return CommonResponse.failure(BasicError.REQ_LIMIT);
        }
        log.info("处理业务");

        return CommonResponse.success();
    }

    public static void main(String[] args) {

        String activityId = "3213";
        String mid = "abc";
        String key = String.format("redis.fangliandian:%s:%s", activityId, mid);

    }
}
