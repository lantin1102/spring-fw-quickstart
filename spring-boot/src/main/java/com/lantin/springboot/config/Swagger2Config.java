package com.lantin.springboot.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.lantin.springboot.config.property.Swagger2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created on 2021/06/15/20:00 周二
 *
 * @author Lantin
 */

@Configuration
@EnableKnife4j
@EnableSwagger2
@EnableConfigurationProperties(Swagger2Properties.class)
public class Swagger2Config {

    @Autowired
    private Swagger2Properties swagger2Properties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                //  包扫描
                .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                //  选择方法上加了注解的
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //  文档名称
                .title("Knife4j APIs接口文档")
                //  文档描述
                .description("Knife4j-ui")
                // 设置访问地址
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .contact(new Contact("Gan Luanqing", null, "lantin1102@gmail.com"))
                .build();
    }
}
