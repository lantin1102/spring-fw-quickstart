package com.lantin.spring.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lantin.spring.util.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author Lantin
 */
// @Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(this.logInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        // resolvers.add(new UnderlineToCamelArgumentResolver());
    }

    //  此方法添加用来格式化的数据类型转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // WebMvcConfigurer.super.addFormatters(registry);
        // registry.addConverter((Converter<String, LocalDateTime>) source -> LocalDateTime.parse(source, DateConfig.FORMATTER_DATE_TIME));
        registry.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                return LocalDateTime.parse(source, DateUtils.FORMATTER_DATE_TIME);
            }
        });
        registry.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return new SimpleDateFormat(DateUtils.DATE_LONG).parse(source);
                } catch (ParseException e) {
                    throw new RuntimeException("date format error with : \"" + source + "\"");
                }
            }
        });
        registry.addConverter(new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                return LocalTime.parse(source, DateUtils.FORMATTER_DATE);
            }
        });
    }

    // 使用extendMessageConverters比configureMessageConverters较好一些
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> iConverter : converters) {
            //  找到MappingJackson2HttpMessageConverter转换器 新建一个无效，推测是按converter中的顺序执行
            //  如果已经有了一个 新加一个在后面是无效的
            if (iConverter instanceof MappingJackson2HttpMessageConverter) {
                //  类型转换
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) iConverter;

                // new一个ObjectMapper会把原有的时间转换实现器去掉，需要全部手写实现自己想要的
                // ObjectMapper objectMapper = converter.getObjectMapper();
                ObjectMapper objectMapper = new ObjectMapper();

                // 统一返回数据和入参的输出风格
                //  设置json的出入参字段风格 这里选择驼峰转下划线风格
                objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
                // objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
                //  序列化时（即封装参数返回时）忽略null字段
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                // 遇到未知变量的异常处理 true为抛异常 （默认值） false为忽略 ，如果设置为true 传入了接收对象里没有的参数也会报错
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // //  设置时区为中国
                // // objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                // // 时间格式 Date类型的格式化格式，不然默认只能接收yyyy-MM-dd的字符串
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.FORMATTER_DATE_TIME));
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.FORMATTER_DATE_TIME));

                //  注意 同一个时间类型不能使用两个格式化格式 后一个会覆盖前一个
                javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateUtils.FORMATTER_DATE));
                javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateUtils.FORMATTER_DATE));
                javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateUtils.FORMATTER_TIME));
                javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateUtils.FORMATTER_TIME));
                //  自己写的时间模块注册进ObjectMapper
                objectMapper.registerModule(javaTimeModule);

                // 把自定义的ObjectMapper放入转换器中
                converter.setObjectMapper(objectMapper);
                //  设置好之后退出循环
                break;
            }
        }
    }
}
