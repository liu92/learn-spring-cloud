package com.learn.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: StorageDao
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:09
 * @History:
 * @<version> 1.0
 */
@Mapper
public interface StorageDao {

    /**
     * 减库存
     *
     * @param productId
     * @param count
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
