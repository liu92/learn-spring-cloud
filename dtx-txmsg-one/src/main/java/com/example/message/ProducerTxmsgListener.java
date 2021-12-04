package com.example.message;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.AccountInfoDao;
import com.example.model.AccountChangeEvent;
import com.example.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 10:18 上午
 * @Author lin
 * @Version 1.0
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1")
public class ProducerTxmsgListener  implements RocketMQLocalTransactionListener {

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    AccountInfoDao accountInfoDao;

    /** 事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
     * @name:
     * @description: 事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
     * @param message 回传的消息，利用transactionId即可获取到该消息的唯一Id
     * @param o  调用send方法时传递的参数，当send时候若有额外的参数可以传递到send方法中，这里能获取到
     * @return: org.apache.rocketmq.spring.core.RocketMQLocalTransactionState
     *      返回事务状态，COMMIT:提交 ROLLBACK:回滚 UNKNOW:回调
     * @date:
     * @author:
     *
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        try {
            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");
            //将accountChange（json）转成AccountChangeEvent
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
            //执行本地事务，扣减金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            // 返回事务状态，COMMIT:提交 ROLLBACK:回滚 UNKNOW:回调
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }


    }

    /** 事务状态回查，查询是否扣减金额
     * @name:
     * @description: 事务状态回查，查询是否扣减金额
     * @param message
     * @return: org.apache.rocketmq.spring.core.RocketMQLocalTransactionState
     *        返回事务状态，COMMIT:提交 ROLLBACK:回滚 UNKNOW:回调
     * @date:
     * @author:
     *
    */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("accountChange");
        //将accountChange（json）转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //事务id
        String txNo = accountChangeEvent.getTxNo();
        int existTx = accountInfoDao.isExistTx(txNo);
        if(existTx>0){
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
