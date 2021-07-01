package com.lantin.spring.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2021/06/08/18:23 周二
 *
 * 下划线转驼峰自定义注解
 *
 * @author Lantin
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamConvert {

}
