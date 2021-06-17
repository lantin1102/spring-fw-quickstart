package com.lantin.springboot.controller;

import com.lantin.springboot.common.CommonResponse;
import com.lantin.springboot.model.ro.UserRo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2021/06/04/10:30 周五
 *
 * @author Lantin
 */


@RestController
@RequestMapping
public class DevController1 {


    @PostMapping("valid/user")
    public CommonResponse<?> validParams(UserRo user) {


        return CommonResponse.success();
    }
}
