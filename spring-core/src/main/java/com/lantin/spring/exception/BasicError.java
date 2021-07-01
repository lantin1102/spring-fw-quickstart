package com.lantin.spring.exception;

/**
 * Created on 2021/06/17/14:18 周四
 *
 * @author Lantin
 */
public enum BasicError implements BaseErrorCode {
    //  参数非法
    INTERNAL_SERVER_ERROR(10005, "服务器内部异常"),
    PARAMS_INVALID(10009, "参数校验不通过");

    private final int code;
    private final String desc;

    BasicError(int code, String desc) {
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
