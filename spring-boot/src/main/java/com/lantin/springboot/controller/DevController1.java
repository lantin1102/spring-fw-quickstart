package com.lantin.springboot.controller;

import com.lantin.springboot.common.CommonResponse;
import com.lantin.springboot.model.ro.UserRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "参数校验Controller")
public class DevController1 {

    @ApiOperation(value = "User参数校验", notes = "Note:User参数校验")
    @ApiImplicitParam(name = "user", value = "用户信息传入参数", required = true, paramType = "form", dataTypeClass = UserRo.class)
    @PostMapping("valid/user")
    public CommonResponse<UserRo> validParams(@Valid UserRo user) {
        log.info(user.toString());

        return CommonResponse.success(user);
    }

    @ApiOperation(value = "User参数校验 in body", notes = "Note:User参数校验")
    @ApiImplicitParam(name = "user", value = "用户信息传入参数", required = true, dataType = "用户对象")
    @PostMapping("valid/user-body")
    public CommonResponse<UserRo> validParamsJson(@RequestBody @Valid UserRo user) {
        log.info(user.toString());

        return CommonResponse.success(user);
    }
}
