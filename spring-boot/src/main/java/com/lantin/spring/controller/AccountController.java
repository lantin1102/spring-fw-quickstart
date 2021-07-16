package com.lantin.spring.controller;

import com.lantin.spring.common.basic.CommonResponse;
import com.lantin.spring.common.exception.AccountError;
import com.lantin.spring.common.exception.BasicError;
import com.lantin.spring.model.Account;
import com.lantin.spring.model.ro.AccountReq;
import com.lantin.spring.service.AccountService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * Created on 2021/07/13/16:12 周二
 *
 * @author Lantin
 */

@RestController
@RequestMapping("account")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/show")
    public CommonResponse<Account> getUserAccount(@RequestParam("id")
                                                  @NotBlank(message = "id不能为空aaaa") String id) {

        if (StringUtils.isBlank(id)) {
            return CommonResponse.failure(BasicError.PARAMS_INVALID);
        }

        Account account = accountService.getUserById(id);

        return CommonResponse.success(account);
    }

    @PostMapping("/save")
    CommonResponse<?> saveUserAccount(@Validated AccountReq account) {
        if (ObjectUtils.isEmpty(account)) {
            return CommonResponse.failure(BasicError.PARAMS_INVALID);
        }
        boolean flag;
        flag = accountService.saveUserAccount(account);
        if (flag) {
            return CommonResponse.success();
        }
        return CommonResponse.failure(AccountError.SAVE_ACCOUNT_FAIL);
    }
}
