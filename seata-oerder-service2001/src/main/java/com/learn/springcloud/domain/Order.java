package com.learn.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 这里的domain 就是和数据库表映射的实体
 *
 * @ClassName: Order
 * @Description: 订单实体类
 * @Author: lin
 * @Date: 2020/8/26 15:26
 * History:
 * @<version> 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态 0:创建中,1:已完结
     */
    private Integer status;
}
