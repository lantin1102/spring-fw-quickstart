package com.lantin.spring.aop;


import com.lantin.spring.common.CommonResponse;
import com.lantin.spring.exception.BasicError;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/06/25/17:56 周五
 *
 * @author Lantin
 */

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.lantin.spring.controller..*Controller.*(..))")
    public void enhanceMethod() {
    }

    @Around("enhanceMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("Controller方法日志开始");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        long methodStartTime = System.currentTimeMillis();
        log.info("request: {}|{}|{}" + className + methodName + methodStartTime);
        Object rtv;
        rtv = new CommonResponse<>(BasicError.INTERNAL_SERVER_ERROR);
        try {
            rtv = joinPoint.proceed();
            return rtv;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return rtv;
        } finally {
            log.info("controller方法日志结束,cost" + (System.currentTimeMillis() - methodStartTime) + "ms");
        }
    }
}
