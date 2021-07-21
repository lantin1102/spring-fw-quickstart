package com.lantin.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/07/01/16:20 周四
 *
 * @author Lantin
 */
@Aspect
@Component
@Slf4j
public class BusinessAspect {

    /**
     * 增强所有Service
     */
    @Pointcut("execution(public * com.lantin.spring.config.service..*Service.*(..)))")
    public void enhanceMethod() {

    }

    @Around("enhanceMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("METHOD START: {}|{}", className, methodName);
        long methodStartTime = System.currentTimeMillis();
        Object rtv;
        try {
            rtv = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("Service方法执行失败", throwable);
            throw throwable;
        } finally {
            log.info("Service METHOD END,cost:{}ms", (System.currentTimeMillis() - methodStartTime));
        }
        return rtv;
    }
}
