package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.OrderDao;
import com.learn.springcloud.domain.Order;
import com.learn.springcloud.service.AccountService;
import com.learn.springcloud.service.OrderService;
import com.learn.springcloud.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: OrderServiceImpl
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:47
 * History:
 * @<version> 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说:
     * 下订单->减库存->减余额->改状态
     * GlobalTransactional seata开启分布式事务,异常时回滚,name保证唯一即可
     *
     * @param order 订单对象
     */
    @Override
    @GlobalTransactional(name = "pre_order_service", rollbackFor = Exception.class)
    public void create(Order order) {
        //1、新建订单
        log.info("------->开始新建订单，全局事务xid:{}", RootContext.getXID());
        orderDao.create(order);

        //2、扣减库存----在执行分之事务之前都会先向TC注册 分支事务
        log.info("------->订单微服务开始调用库存，进行扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());


        if (20 == order.getCount()) {
            throw new RuntimeException("store server exception");
        }


        log.info("------->订单微服务开始调用库存，做扣减end");

        //3、扣减账户余额 --- 同样也是 要先向TC注册分支事务，同时携带全局事id
        // TC返回一个全局事务id, 并且seate提供一个代理 来写入undo_log日志 然后提交事务
        // 最后将分支事务处理结果上报给 TC(事务协调器)
        // 分支事务都做完了 之后， TM(事务管理器) 发起提交事务 给 TC, 所以 TC来 提交全部事务(
        // 因为在分支事务中已经把事务提交完来，所以再次提交事务不会扣减库存后 扣减账户金额数据了，
        // 在这个阶段1已经处理好了，只需要将undo_log日志删除好就可以了)
        log.info("------->订单微服务开始调用账户服务，做账户余额扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------->订单微服务开始调用账户服务，做账户余额扣减end");

        //4、修改订单状态，从零到1，1代表已经完成,这里传入0，是根据这个用户id和这个状态标识条件来更新
        log.info("------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("------->修改订单状态结束");

        log.info("----->下订单结束了,O(∩_∩)O哈哈~");
    }


}
