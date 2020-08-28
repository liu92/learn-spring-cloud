package com.learn.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName: Account
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:17
 * History:
 * @<version> 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    /**
     * id
     */
    private Long id;
    /**
     * 产品id
     */
    private Long userId;

    /**
     * 总库存
     */
    private BigDecimal total;

    /**
     * 已用库存
     */
    private BigDecimal used;

    /**
     * 剩余库存
     */
    private BigDecimal residue;

}
