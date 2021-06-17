package com.lantin.springboot.model.ro;


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
public class UserRo {

    // MultipartFile file;
    @NotEmpty(message = "姓名不能为空")
    String name;
    @NotNull(message = "年龄不能为空")
    Integer age;
}