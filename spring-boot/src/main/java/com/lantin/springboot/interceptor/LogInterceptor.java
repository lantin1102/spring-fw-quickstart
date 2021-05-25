package com.lantin.springboot.interceptor;

import com.lantin.springboot.util.LogAsyncUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private LogAsyncUtil logAsyncUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logAsyncUtil.processLog();

        System.out.println("日志处理完毕");
        return true;
    }
}
