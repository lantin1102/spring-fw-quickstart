package com.lantin.springboot.util;

import com.lantin.springboot.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class LogAsyncUtil {

    @Qualifier("logServiceImpl2")
    @Autowired
    private LogService logService;

    public void processLog(){

        logService.appendLog();
    }
}
