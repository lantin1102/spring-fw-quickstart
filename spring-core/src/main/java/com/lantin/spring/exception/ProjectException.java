package com.lantin.spring.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created on 2021/06/07/18:56 周一
 * 自定义项目全局异常类
 *
 * @author Lantin
 */

@Getter
@Setter
public class ProjectException extends RuntimeException {

    protected int errorCode;

    protected String errorMsg;

    public ProjectException() {
    }

    public ProjectException(BaseErrorCode errorInfo) {
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMsg();
    }

    public ProjectException(BaseErrorCode errorInfo, Throwable cause) {
        super(errorInfo.getMsg(), cause);
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMsg();
    }

    public ProjectException(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public void setMessage(String msg) {
        this.errorMsg = msg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}