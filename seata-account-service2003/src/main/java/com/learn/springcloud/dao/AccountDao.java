package com.learn.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @ClassName: AccountDao
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 17:17
 * @History:
 * @<version> 1.0
 */
@Mapper
public interface AccountDao {

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId,
                  @Param("money") BigDecimal money);
}
