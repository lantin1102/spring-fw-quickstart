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
    @Pointcut("execution(public * com.lantin.spring.service..*Service.*(..)))")
    public void enhanceMethod() {

    }

    @Around("enhanceMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("Service方法日志开始");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        long methodStartTime = System.currentTimeMillis();
        log.info("request: {}|{}|{}" + className + methodName + methodStartTime);
        Object rtv;
        rtv = new CommonResponse<>(BasicError.INTERNAL_SERVER_ERROR);
        try {
            rtv = joinPoint.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("Service方法执行失败", throwable);
        } finally {
            log.info("Service方法日志结束,cost" + (System.currentTimeMillis() - methodStartTime) + "ms");
        }
        return rtv;
    }
}
