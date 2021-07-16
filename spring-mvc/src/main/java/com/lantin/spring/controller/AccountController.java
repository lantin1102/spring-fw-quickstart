package com.lantin.spring.controller;

import com.lantin.spring.core.basic.CommonResponse;
import com.lantin.spring.model.Account;
import com.lantin.spring.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
