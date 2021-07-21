package com.lantin.spring.config.service.impl;


import com.lantin.spring.mapper.AccountMapper;
import com.lantin.spring.model.Account;
import com.lantin.spring.config.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Account getUserById(String id) {

        return accountMapper.selectByPrimaryKey(Integer.parseInt(id));
    }
}
