package com.lantin.spring.handler;


import com.lantin.spring.common.utils.ParamConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2021/06/08/18:25 周二
 * 自定义属性绑定解析器，用于解析post请求的表单提交，前端参入参数下划线转驼峰
 *
 * @author Lantin
 */
@Slf4j
public class UnderlineToCamelArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 匹配下划线 _ 加任意一个字符
     */
    private static final Pattern UNDER_LINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 设置此转换器使用范围 ，添加了 {@link ParamConvert @ParamConvert}注解的参数
     *
     * @param parameter 传入参数的实体类
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(ParamConvert.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return handleParameterNames(parameter, webRequest);
    }

    private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {
        Iterator<String> parameterNames = webRequest.getParameterNames();
        Object obj = null;
        try {
            obj = parameter.getParameterType().getDeclaredConstructor().newInstance();
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
            while (parameterNames.hasNext()) {
                String paramName = parameterNames.next();
                Object paramValue = webRequest.getParameter(paramName);
                // 参数名称从下划线转驼峰
                String camelName = nameFromUnderLineToCamel(paramName);
                Field field = obj.getClass().getField(camelName);
                Class<?> type = field.getType();
                if (type.equals(Timestamp.class)) {

                } else {
                    wrapper.setPropertyValue(camelName, paramValue);
                }
            }
            return obj;
        } catch (Exception e) {
            log.error("创建实体类失败：type:{}", parameter.getParameterType().getName(), e);
        }
        return obj;
    }

    private String nameFromUnderLineToCamel(String source) {

        Matcher matcher = UNDER_LINE_PATTERN.matcher(source);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
