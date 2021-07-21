package com.lantin.spring.config.service;

import com.lantin.spring.model.Account;
import com.lantin.spring.model.ro.AccountReq;

/**
 * Created on 2021/07/07/11:55 周三
 *
 * @author Lantin
 */
public interface AccountService {

    /**
     * 获取用户账户信息
     *
     * @param id 主键id
     * @return 账户信息
     */
    Account getUserById(String id);

    /**
     *
     * @param account 账户封装类
     */
    boolean saveUserAccount(AccountReq account);
}
