package com.example.service;

import com.example.model.AccountChangeEvent;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 9:58 上午
 * @Author lin
 * @Version 1.0
 */
public interface AccountInfoService {
    //向mq发送转账消息
    void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent);
    //更新账户，扣减金额
    void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

}
