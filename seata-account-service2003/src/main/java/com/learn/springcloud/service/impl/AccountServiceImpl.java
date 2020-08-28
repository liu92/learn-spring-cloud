package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.AccountDao;
import com.learn.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AccountServiceImpl
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:17
 * History:
 * @<version> 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------>account-service中扣减余额开始");
        //模拟超时异常，全局事务回滚
//        try {
//            //暂停20秒钟
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        LOGGER.info("------>storage-service中扣减余额开始");
    }
}
