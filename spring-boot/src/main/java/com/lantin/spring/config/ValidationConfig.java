package com.lantin.spring.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created on 2021/07/14/14:01 周三
 * validated校验配置
 * 验证消息提示国际化
 *
 * @author Lantin
 */
@Configuration
public class ValidationConfig {

    @Bean
    public Validator getValidator() {

        //  自定义配置文件路径 ,不设置的话默认需要把配置文件放在resources下,文件名必须叫ValidationMessages.properties
        ResourceBundleMessageInterpolator resourceBundleMessageInterpolator = new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("i18n/message-validation"));
        return Validation
                /*
                 *  使用HibernateValidator可以设置是否校验到第一个不符合就返回
                 *  .byProvider(HibernateValidator.class)
                 *   校验到第一个不符合就返回
                 *   .failFast(true)
                 * */
                .byDefaultProvider()
                .configure()
                .messageInterpolator(resourceBundleMessageInterpolator)
                .buildValidatorFactory()
                .getValidator();
    }
}
