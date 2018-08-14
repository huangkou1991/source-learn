package com.learn.jedis.service;

import com.learn.jedis.conn.RedisConn;
import com.learn.jedis.util.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @author :lwy
 * @date 2018/6/8 19:25
 */
public class HelloService {




    public void toHello() throws InterruptedException {
        Jedis jedis=RedisConn.getJedis();
        boolean result=JedisUtil.tryAcquireLock(jedis,"hello4",2);
        if (result){
            System.out.println("hello");
        }
        //释放锁
        boolean release=JedisUtil.releaseLock(jedis,"hello4");

        if(release){
            //释放成功
        }else{
            //释放失败  --保证释放，可以设置key的过期时间
        }
    }


    public static void main(String[] args) throws InterruptedException {
        HelloService service=new HelloService();
        service.toHello();
    }
}
