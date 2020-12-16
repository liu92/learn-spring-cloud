package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.PaymentDao;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentServiceImpl
 * @Description:
 * @Author: lin
 * @Date: 2020/8/15 22:16
 * History:
 * @<version> 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    /**
     * 插入
     *
     * @param payment
     */
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
