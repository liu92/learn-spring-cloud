package com.learn.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName: PaymentFallbackService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/19 17:14
 * History:
 * @<version> 1.0
 */
@Component
public class PaymentFallbackService implements  PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        String result ="------PaymentFallbackService fall back-paymentInfo_OK, o(╥﹏╥)o";
        return result;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        String result ="----PaymentFallbackService fall back-paymentInfo_TimeOut,o(╥﹏╥)o";
        return result;
    }
}
