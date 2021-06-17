package com.lantin.springboot.exception;

/**
 * Created on 2021/06/07/18:34 周一
 *
 * @author Lantin
 */
public interface BaseErrorCode {

    /**
     * 返回状态码
     *
     * @return int
     */
    int getCode();

    /**
     * 返回异常的描述信息
     *
     * @return java.lang.String
     */
    String getMsg();
}