package com.learn.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自行实现负载均衡算法接口
 *
 * @ClassName: LoadBalancer
 * @Description:
 * @Author: lin
 * @Date: 2020/8/18 10:44
 * @History:
 * @<version> 1.0
 */
public interface LoadBalancer {

    /**
     * 定义服务实例，也就是微服务中的 服务实例
     *
     * @param serviceInstances
     * @return
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
