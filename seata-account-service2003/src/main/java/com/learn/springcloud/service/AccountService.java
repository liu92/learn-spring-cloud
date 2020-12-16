package com.learn.springcloud.service;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @ClassName: AccountService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:17
 * History:
 * @<version> 1.0
 */
public interface AccountService {

    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     */
    void decrease(Long userId, BigDecimal money);
}
