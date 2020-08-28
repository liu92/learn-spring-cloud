package com.learn.springcloud.dao;

import com.learn.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: OrderDao
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:36
 * History:
 * @<version> 1.0
 */
@Mapper
public interface OrderDao {

    /**
     * 1、新建订单
     * @param order
     */
    void  create(Order order);
    /**
     * 2、修改订单状态，从0改为1
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status")Integer status);
}
