package com.lantin.springboot.controller;

import com.lantin.springboot.common.CommonResponse;
import com.lantin.springboot.model.ro.UserRo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created on 2021/06/04/10:30 周五
 *
 * @author Lantin
 */


@RestController
@RequestMapping
@Slf4j
public class DevController1 {


    @PostMapping("valid/user")
    public CommonResponse<?> validParams(@Valid UserRo user) {


        log.info(user.toString());

        return CommonResponse.success(user);
    }
}
