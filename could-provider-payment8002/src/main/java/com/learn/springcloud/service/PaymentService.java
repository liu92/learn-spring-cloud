package com.learn.springcloud.service;

import com.learn.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PaymentService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/15 22:16
 * @History:
 * @<version> 1.0
 */

public interface PaymentService {

    /**
     * 插入
     * @param payment
     */
    int create(Payment payment);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
