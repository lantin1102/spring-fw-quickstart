package com.lantin.spring.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created on 2021/06/09/17:19 周三
 *
 * @author Lantin
 */
public class CustomizeDateConvert implements Converter<String, Date> {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_DATE_FORMATTER = "yyyy-MM-dd";
    private static final String TIMESTAMP_FORMATTER = "^\\d+$";


    @Override
    public Date convert(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        try {
            if (value.contains("-")) {
                DateTimeFormatter formatter;
                if (value.contains(":")) {
                    formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
                } else {
                    formatter = DateTimeFormatter.ofPattern(SHORT_DATE_FORMATTER);
                }
                LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);
                ZoneId zoneId = ZoneId.systemDefault();
                Instant instant = localDateTime.atZone(zoneId).toInstant();
                return Date.from(instant);
            } else if (value.matches(TIMESTAMP_FORMATTER)) {
                long iDate = Long.parseLong(value);
                return new Date(iDate);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("parse %s to Date Fail", value));
        }
        throw new RuntimeException(String.format("parse %s to Date Fail", value));

    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
