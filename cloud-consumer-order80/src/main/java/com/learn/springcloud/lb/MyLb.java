package com.learn.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现接口，并在这个方法里面写Ribbon轮询算法
 * @ClassName: MyLb
 * @Description:
 * @Author: lin
 * @Date: 2020/8/18 10:49
 * History:
 * @<version> 1.0
 */
@Component
public class MyLb implements LoadBalancer{
    /**
     * 定义一个变量，这个变量再 进行比较并设置的时候需要用
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 这个方法的目的是获取 就是获取rest接口第几次请求数
     * @return
     */
    public final  int getAndIncrement(){
        int current = 0;
        int next = 0;
        do{
            //获取当前值
            current = this.atomicInteger.get();
            //判断当前是否超过整形int的最大值，如果超过就从0重新开始，如果没有那么就+1；
            int maxSize = 2147483647;
            next = current >= maxSize ? 0 : current + 1;
            //自旋操作
            // 如果内存值和当前值相同，那么久返回next。 这里取反 就表示不在进行循环操作了
        }while (!atomicInteger.compareAndSet(current, next));
        System.out.println("****第几次访问,次数next:" + next);
        return  next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口计数从1开始。
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 这个计算和Ribbon的思路一样， 获取到请求次数 然后和 服务实例数据进行 取余操作。
        // 然后在 取余操作之后的 得到的数据 到 服务实例集合中去获取服务实例
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
