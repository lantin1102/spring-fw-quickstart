package com.lantin.springboot.model.ro;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Created on 2021/06/07/15:10 周一
 *
 * @author Lantin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户信息传入参数封装类", value = "用户对象")
public class UserRo {

    // MultipartFile file;
    @NotEmpty(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    String name;
    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄")
    Integer age;
}