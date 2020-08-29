package com.learn.springcloud.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用hutool工具包来生成雪花算法
 * @ClassName: IdGeneratorSnowflake
 * @Description:
 * @Author: lin
 * @Date: 2020/8/29 18:46
 * History:
 * @<version> 1.0
 */
@Component
@Slf4j
public class IdGeneratorSnowflake {
     private long workerId = 0;
     private long dataCenterId = 1;

     private Snowflake snowflake = IdUtil.createSnowflake(workerId ,dataCenterId);

     @PostConstruct
     public void init(){
        try {
             workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
             log.info("当前机器的workerId: {}", workerId);
        }catch (Exception e){
              e.printStackTrace();
              log.info("当前机器的workerId获取失败",e);
              workerId = NetUtil.getLocalhostStr().hashCode();
        }

     }


     public synchronized long snowflakeId() {
          return snowflake.nextId();
     }

     public synchronized long snowflakeId(long workerId, long dataCenterId) {
          snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
          return snowflake.nextId();
     }


    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowflake().snowflakeId());
    }
}

