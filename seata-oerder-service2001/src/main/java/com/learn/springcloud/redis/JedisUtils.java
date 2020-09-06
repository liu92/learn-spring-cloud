package com.learn.springcloud.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @ClassName: JedisUtils
 * @Description:
 * @Author: lin
 * @Date: 2020/9/1 15:20
 * History:
 * @<version> 1.0
 */
public class JedisUtils {
   private static JedisPool jp =null;
   private static String host = null;
   private static int port;
   private static  int maxTotal;
   private static  int maxIdle;

   static {
       ResourceBundle rb = ResourceBundle.getBundle("redis");
       host = rb.getString("redis.host");
       port = Integer.parseInt(rb.getString("redis.port"));
       maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));
       maxIdle = Integer.parseInt(rb.getString("redis.maxIdle"));

       JedisPoolConfig jpc = new JedisPoolConfig();
       jpc.setMaxTotal(maxTotal);
       jpc.setMaxIdle(10);
       jp = new JedisPool(jpc, host, port);
   }

   public  static Jedis getJedis(){
       return  jp.getResource();
   }

   public static void main(String[] args) {
       JedisUtils.getJedis();
   }
}
