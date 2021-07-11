package com.lantin.spring.mapper;

import com.lantin.spring.BaseJunitTest;
import com.lantin.spring.model.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2021/07/11/22:13 周日
 *
 * @author Lantin
 */


public class AccountMapperTest extends BaseJunitTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void selectByPrimaryKey() {
        Integer id = 1;
        Account account = accountMapper.selectByPrimaryKey(id);
        System.out.println(account);
    }
}