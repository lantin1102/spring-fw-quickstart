package com.lantin.spring.config.service.impl;

import com.lantin.spring.config.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created on 2021/07/01/17:54 周四
 *
 * @author Lantin
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Override
    public String demoService() {

        return "service处理业务";
    }
}
