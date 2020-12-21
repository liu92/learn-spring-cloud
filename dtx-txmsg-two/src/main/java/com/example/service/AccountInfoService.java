package com.example.service;

import com.example.model.AccountChangeEvent;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 10:14 上午
 * @Author lin
 * @Version 1.0
 */
public interface AccountInfoService {
    //更新账户，增加金额
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent);
}
