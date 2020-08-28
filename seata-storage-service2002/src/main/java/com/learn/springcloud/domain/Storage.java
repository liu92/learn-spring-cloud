package com.learn.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Storage
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:10
 * History:
 * @<version> 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    /**
     * id
     */
    private Long id;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

}
