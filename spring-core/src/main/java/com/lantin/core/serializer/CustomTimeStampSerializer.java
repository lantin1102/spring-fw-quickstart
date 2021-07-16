package com.lantin.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lantin.common.utils.DateUtils;


import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created on 2021/07/07/16:25 周三
 * 将时间类型对象序列化为long时间戳返回
 *
 * @author Lantin
 */
public class CustomTimeStampSerializer<T> extends JsonSerializer<T> {

    //  alibaba fastjson序列化
    // @Override
    // public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
    //     if (object == null) {
    //         serializer.write(null);
    //     }
    //     if (object instanceof LocalDateTime) {
    //         LocalDateTime time = (LocalDateTime) object;
    //         serializer.write(DateUtils.dateTimeToMillis(time));
    //     }
    // }


    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNumber("");
        }
        if (value instanceof LocalDateTime) {
            LocalDateTime time = (LocalDateTime) value;
            gen.writeNumber(DateUtils.dateTimeToMillis(time));
        }
    }
}
