package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.StorageDao;
import com.learn.springcloud.service.StorageService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName: StorageServiceImpl
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:19
 * History:
 * @<version> 1.0
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     */
    @Override
    @Transactional
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------>storage-service中扣减库存开始，全局事务xid:{}", RootContext.getXID());
        storageDao.decrease(productId, count);

        LOGGER.info("------>storage-service中扣减库存结束");
    }
}
