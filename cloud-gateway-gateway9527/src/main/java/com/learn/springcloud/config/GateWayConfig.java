package com.learn.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: GateWayConfig
 * @Description:
 * @Author: lin
 * @Date: 2020/8/20 14:59
 * History:
 * @<version> 1.0
 */
@Configuration
public class GateWayConfig {


    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址 http://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //这里的id就表示 routes中id
        routes.route("path_route_t2",
                //这里的guonei 就表示请求地址后的接口名
                r -> r.path("/guonei")
                        //请求地址
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }


    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route-t2",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }


}
