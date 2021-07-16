package com.lantin.spring.common.exception;

/**
 * Created on 2021/07/14/12:08 周三
 *
 * @author Lantin
 */
public enum AccountError implements BaseErrorCode {
    /**
     * 账户相关异常
     */
    SAVE_ACCOUNT_FAIL(11001, "创建账户失败");

    private final int code;
    private final String desc;

    AccountError(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return desc;
    }
}
