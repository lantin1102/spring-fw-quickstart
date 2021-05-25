package com.lantin.springboot.controller;


import com.lantin.springboot.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LogController {

    @Qualifier("logServiceImpl2")
    @Autowired
    LogService logService;

    @RequestMapping("demo")
    public String processLog() {

        System.out.println("正在执行的线程为：" + Thread.currentThread().getName());
        String s = logService.appendLog();
        System.out.println(s);
        return "hello spring boot:"+s;
    }

}
