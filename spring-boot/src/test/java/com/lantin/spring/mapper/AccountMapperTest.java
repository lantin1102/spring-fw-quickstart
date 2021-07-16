package com.lantin.spring.mapper;

import com.lantin.spring.BootApplicationTests;
import com.lantin.spring.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2021/07/13/16:21 周二
 *
 * @author Lantin
 */
class AccountMapperTest extends BootApplicationTests {

    @Autowired
    AccountMapper accountMapper;

    @Test
    void insert() throws InterruptedException {
        Account account = new Account();
        account.setUsername("wangwu7");
        account.setMoney(10000d);
        System.out.println("插入前获取主键id：" + account.getId());
        int insert = accountMapper.insert(account);
        System.out.println("插入后获取主键id：" + account.getId());
        System.out.println(insert);
        Thread.sleep(100);

        // System.out.println("插入后获取主键id："+account.getId());
        // insert = accountMapper.insert(account);
        // System.out.println("插入后获取主键id："+account.getId());
        // System.out.println(insert);

    }
    @Test
    void insertSelective() {
    }
}