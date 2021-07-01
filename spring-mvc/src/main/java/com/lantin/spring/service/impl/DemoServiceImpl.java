package com.lantin.spring.service.impl;

import com.lantin.spring.service.DemoService;
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
