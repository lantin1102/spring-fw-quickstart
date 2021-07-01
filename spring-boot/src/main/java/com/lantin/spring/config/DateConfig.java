package com.lantin.spring.config;

import com.lantin.spring.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created on 2021/06/10/17:59 周四
 * 自定义日期参数转换器的Converter 日期字符串转时间类型 传递封装
 * <p>
 * 可以在@Configuration里 @Bean配置
 * 也可以 在WebMVCConfig中 重写 addFormatter方法添加Convert
 *
 * @author Lantin
 */
// @Configuration
public class DateConfig {


    // @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String timeStr) {
                return LocalDateTime.parse(timeStr, DateUtils.FORMATTER_DATE_TIME);
            }
        };
        // return timeStr -> LocalDateTime.parse(timeStr, FORMATTER_DATE_TIME);
    }

    // @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String timeStr) {
                try {
                    return new SimpleDateFormat(DateUtils.DATE_LONG).parse(timeStr);
                } catch (ParseException e) {
                    throw new RuntimeException("date format error with : \"" + timeStr + "\"");
                }
            }
        };
    }

    // @Bean
    public Converter<String, LocalTime> localTimeConverter() {

        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                return LocalTime.parse(source, DateUtils.FORMATTER_TIME);
            }
        };
    }
}
