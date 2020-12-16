package com.learn.springcloud.service;

import com.learn.springcloud.domain.Order;

/**
 * @ClassName: OrderService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:37
 * @History:
 * @<version> 1.0
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     */
    void create(Order order);


}
