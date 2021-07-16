package com.lantin.spring.model.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created on 2021/07/14/11:46 周三
 *
 * @author Lantin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountReq {

    @NotEmpty(message = "{NotEmpty.AccountReq.username}")
    @Size(max = 15, message = "{Size.AccountReq.username}")
    private String username;

    @Min(value = 0,message ="{Min.AccountReq.money}")
    @NotNull(message = "{NotNull.AccountReq.money}")
    private Double money;

}
