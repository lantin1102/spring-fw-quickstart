package com.lantin.spring.common.basic;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lantin.spring.common.exception.BaseErrorCode;
import com.lantin.spring.common.exception.ProjectException;
import com.lantin.spring.common.serializer.CustomTimeStampSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lantin
 */
@Data
public class CommonResponse<T> {

    private static final int COMMON_FAIL_CODE = -1;
    private static final int COMMON_SUCCESS_CODE = 0;
    private static final String COMMON_FAIL_MSG = "request fail";

    private int code;
    private T data;
    private String message;
    /**
     * 序列化为long 时间戳
     */
    @JsonSerialize(using = CustomTimeStampSerializer.class)
    private LocalDateTime ts;
    // private String requestId;

    public CommonResponse() {

    }

    public CommonResponse(int code) {
        this.code = code;
        this.ts = LocalDateTime.now();
    }

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.ts = LocalDateTime.now();
    }

    public CommonResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.ts = LocalDateTime.now();
    }

    public CommonResponse(BaseErrorCode error) {
        this.code = error.getCode();
        this.message = error.getMsg();
        this.ts = LocalDateTime.now();
    }

    public CommonResponse(T data) {
        this.code = COMMON_SUCCESS_CODE;
        this.data = data;
        this.ts = LocalDateTime.now();
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(COMMON_SUCCESS_CODE);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data);
    }

    public static <T> CommonResponse<T> success(T data, String msg) {
        return new CommonResponse<>(COMMON_SUCCESS_CODE, msg, data);
    }

    public static <T> CommonResponse<T> failure(ProjectException e) {
        return new CommonResponse<>(e.getErrorCode(), e.getErrorMsg());
    }

    public static <T> CommonResponse<T> failure(int errorCode, String errorMsg) {
        return new CommonResponse<>(errorCode, errorMsg);
    }

    public static <T> CommonResponse<T> failure() {
        return new CommonResponse<>(COMMON_FAIL_CODE, COMMON_FAIL_MSG);
    }

    public static <T> CommonResponse<T> failure(String errorCode) {
        return new CommonResponse<>(COMMON_FAIL_CODE, errorCode);
    }

    public static <T> CommonResponse<T> failure(BaseErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getCode(), errorCode.getMsg());
    }
}