package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 10:02 上午
 * @Author lin
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountChangeEvent implements Serializable {

    private static final long serialVersionUID = -4114120819615429605L;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 变动金额
     */
    private double amount;
    /**
     * 事务号
     */
    private String txNo;
}
