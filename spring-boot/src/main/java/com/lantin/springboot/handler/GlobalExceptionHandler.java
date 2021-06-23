package com.lantin.springboot.handler;

import com.lantin.springboot.common.CommonResponse;
import com.lantin.springboot.exception.BasicError;
import com.lantin.springboot.exception.ProjectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Created on 2021/06/17/13:53 周四
 *
 * @author Lantin
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数绑定异常处理
     * 主要处理以下几个异常：
     * ConstraintViolationException：普通参数
     * MethodArgumentNotValidException：RequestBody参数
     * MethodArgumentTypeMismatchException: 参数类型不匹配
     * BindException 请求参数绑定到对象参数
     * ServletRequestBindingException 必填参数缺失
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            BindException.class,
            ServletRequestBindingException.class})
    public CommonResponse<Throwable> paramValidateExceptionHandler(Exception e) {
        String msg;
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            msg = exception.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            msg = exception.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) e;
            msg = String.format("参数[%s]不能为空", exception.getParameterName());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            msg = String.format("参数[%s]类型错误", exception.getName());
        } else {
            msg = "必填参数缺失";
        }
        log.error("参数校验不通过,msg:{}", msg);
        return CommonResponse.failure(BasicError.PARAMS_INVALID.getCode(), msg);
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(ProjectException.class)
    public CommonResponse<Throwable> projectExceptionHandler(ProjectException e) {
        return CommonResponse.failure(e);
    }

    /**
     * 其他异常以及框架级别异常返回
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse<Throwable> frameworkExceptionHandler(Exception e) {
        return CommonResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());
    }
}
