package com.lantin.spring.controller;

import com.lantin.spring.common.basic.CommonResponse;
import com.lantin.spring.common.exception.BasicError;
import com.lantin.spring.model.Account;
import com.lantin.spring.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2021/07/07/11:58 周三
 *
 * @author Lantin
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{id}")
    public CommonResponse<Account> getUserAccount(@PathVariable("id") String id) {

        if (StringUtils.isBlank(id)) {
            return CommonResponse.failure(BasicError.PARAMS_INVALID);
        }

        Account account = accountService.getUserById(id);

        return CommonResponse.success(account);
    }

}
