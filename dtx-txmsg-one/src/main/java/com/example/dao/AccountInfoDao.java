package com.example.dao;

import com.example.entity.AccountInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 9:56 上午
 * @Author lin
 * @Version 1.0
 */
@Mapper
@Component
public interface AccountInfoDao {

    @Update("update account_info set account_balance=account_balance+#{amount} where account_no=#{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);


    @Select("select * from account_info where where account_no=#{accountNo}")
    AccountInfo findByIdAccountNo(@Param("accountNo") String accountNo);



    @Select("select count(1) from de_duplication where tx_no = #{txNo}")
    int isExistTx(String txNo);


    @Insert("insert into de_duplication values(#{txNo},now());")
    int addTx(String txNo);

}
