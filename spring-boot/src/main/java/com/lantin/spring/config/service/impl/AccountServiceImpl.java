package com.lantin.spring.config.service.impl;


import com.lantin.spring.mapper.AccountMapper;
import com.lantin.spring.model.Account;
import com.lantin.spring.model.ro.AccountReq;
import com.lantin.spring.config.service.AccountService;
import com.lantin.spring.utils.convert.AccountConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2021/07/07/11:57 周三
 *
 * @author Lantin
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getUserById(String id) {

        return accountMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public boolean saveUserAccount(AccountReq accountReq) {

        Account account = AccountConvert.HANDLER.req2Entity(accountReq);

        int i = accountMapper.insertSelective(account);
        return i > 0;
    }
}
