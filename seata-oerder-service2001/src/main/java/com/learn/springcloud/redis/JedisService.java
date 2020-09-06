package com.learn.springcloud.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * @ClassName: JedisService
 * @Description:
 * @Author: lin
 * @Date: 2020/9/1 14:39
 * History:
 * @<version> 1.0
 */
public class JedisService {

    private String id;
    private int num;
    public  JedisService(String id, int num){
        this.id = id;
        this.num = num;
    }

    public void service(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String value = jedis.get("compid:" + id);
        try {
            if(value == null){
                //不存在，创建该值 设置过期时间5s
                jedis.setex("compid:" + id, 5, Long.MAX_VALUE-num + "");
            }else {
                Long val = jedis.incr("compid:" + id);
                business(id, num-(Long.MAX_VALUE-val));
            }
        }catch (JedisDataException e){
            System.out.println("使用已经到达次数上限，请升级会员级别");
            return;
        }finally {
            jedis.close();
        }
    }


    public void  business(String id, Long value){
        System.out.println("用户:"+id+" 业务操作执行第："+ value+"次");
    }

}

class MyThread extends Thread{
    JedisService service ;

    public  MyThread(String id, int num){
        service = new JedisService(id, num);
    }

    @Override
    public void run() {
        while (true){
            service.service();
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MainTest{
    public static void main(String[] args) {
        MyThread mt = new MyThread("初级用户", 10);
        MyThread mt1 = new MyThread("高级用户", 30);
        mt.start();
        mt1.start();
    }
}