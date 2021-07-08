package com.lantin.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lantin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 账户余额
     */
    private Double money;

    private static final long serialVersionUID = 1L;

}