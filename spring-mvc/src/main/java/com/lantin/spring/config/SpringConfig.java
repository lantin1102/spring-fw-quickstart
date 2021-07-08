package com.lantin.spring.config;

import com.lantin.spring.aop.ControllerAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2021/07/01/17:16 周四
 * 声明spring配置 相当于application-context.xml
 *
 * @author Lantin
 */

// @Configuration
// @ComponentScan(value = "com.lantin.spring", excludeFilters = {
//         @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ControllerAspect.class),
//         @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, RestController.class})
// })
// public class SpringConfig {
//
//
// }
