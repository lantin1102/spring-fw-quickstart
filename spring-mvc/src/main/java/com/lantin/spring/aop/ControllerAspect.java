package com.lantin.spring.aop;


import com.lantin.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        HttpServletRequest request = ServletUtils.getRequest();
        String requestUri = request.getRequestURI();
        long methodStartTime = System.currentTimeMillis();
        log.info("request: {}|{}|{}", className, requestUri, methodName);
        Object rtv;
        try {
            rtv = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        } finally {
            log.info("controller方法日志结束,cost:{}ms", (System.currentTimeMillis() - methodStartTime));
        }
        return rtv;
    }
}
