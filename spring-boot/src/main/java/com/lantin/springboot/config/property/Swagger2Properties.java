package com.lantin.springboot.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2021/06/15/20:12 周二
 *
 * @author Lantin
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "lantin.framework.swagger2")
public class Swagger2Properties {

    public String basePackage;
}
