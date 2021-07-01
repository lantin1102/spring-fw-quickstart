package com.lantin.spring.service;

/**
 * Created on 2021/06/23/18:00 周三
 *
 * @author Lantin
 */
public interface RedisLockService {


    void jobDoing(String uid);

}
