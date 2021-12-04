package com.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.AccountInfoDao;
import com.example.model.AccountChangeEvent;
import com.example.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 10:03 上午
 * @Author lin
 * @Version 1.0
 */
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /** 向mq发送转账消息
     * @name:
     * @description: 向mq发送转账消息
     * @param accountChangeEvent
     * @return: void
     * @date:
     * @author:
     *
    */
    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //将accountChangeEvent转成json
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        String jsonString = jsonObject.toJSONString();
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();
        //发送一条事务消息
        /**
         * String txProducerGroup 生产组
         * String destination topic，以前的版本有这个参数
         * Message<?> message, 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1",
                "topic_txmsg",message,null);



    }

    /** 更新账户，扣减金额
     * @name:  
     * @description: 更新账户，扣减金额
     * @param accountChangeEvent
     * @return: void
     * @date:  
     * @author: 
     * 
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {

        log.info("bank1更新本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),
                accountChangeEvent.getAmount());
        /**
         * 幂等判断
         */
        if(accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0){
            return ;
        }
        //扣减金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),
                accountChangeEvent.getAmount() * -1);
        //添加事务日志
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        // 生产者 中如果 发生了异常 ，那么消息就不会发送出去。
        // 因为本地事务和消息发送是 原子性
        if(accountChangeEvent.getAmount() == 3){
            throw new RuntimeException("人为制造异常");
        }
    }
}
