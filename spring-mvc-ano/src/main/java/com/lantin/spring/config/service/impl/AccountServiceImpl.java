package com.lantin.spring.config.service.impl;

import com.lantin.spring.config.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Created on 2021/07/21/16:51 周三
 *
 * @author Lantin
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Override
    public void saveAccount() {
        System.out.println("保存账户成功");
    }
}
