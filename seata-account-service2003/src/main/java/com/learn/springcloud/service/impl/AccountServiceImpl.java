package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.AccountDao;
import com.learn.springcloud.service.AccountService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    @Transactional
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------>account-service中扣减余额开始,全局事务xid:{}", RootContext.getXID());
        //模拟超时异常，全局事务回滚
//        try {
//            //暂停
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        LOGGER.info("------>storage-service中扣减余额开始");
    }
}
