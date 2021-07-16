package com.lantin.spring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2021/07/16/16:05 周五
 *
 * @author Lantin
 */
@Component
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Test拦截器执行了");

        return HandlerInterceptor.super.preHandle(request, response, handler);


    }
}
