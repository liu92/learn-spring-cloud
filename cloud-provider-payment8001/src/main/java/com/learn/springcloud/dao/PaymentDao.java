package com.learn.springcloud.dao;

import com.learn.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PaymentDao
 * @Description:
 * @Author: lin
 * @Date: 2020/8/15 22:07
 * @History:
 * @<version> 1.0
 * 推荐使用 @Mapper ，这个注解，如果使用spring框架，在插入的时候有时会有我呢提
 */
@Mapper
public interface PaymentDao {

    /**
     * 插入
     *
     * @param payment
     */
    int create(Payment payment);


    /**
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
