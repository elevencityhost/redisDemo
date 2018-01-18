package com.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * function 测试jedis连接redis并执行部分操作
 * @author 十一城城主
 * @data 2018/1/18 13:18
 */
public class JedisDemo {

    /*public static void main(String[] args) {
        //设置IP地址和端口
        Jedis jedis = new Jedis("39.106.173.68",6379);
        //存入数据
        jedis.set("say","hello redis!");
        //获取数据
        String s = jedis.get("say");
        System.out.println(s);
        //释放资源
        jedis.close();
    }*/
    /**
    　　* 使用连接池的方式
    　　* @author 十一城城主
    　　* @date 2018/1/18 17:20
    　　*/
    public static void main(String[] args) {
        //获得连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(30);
        //设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(20);
        //获得连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"39.106.173.68",6379);
        Jedis jedis = null;
        try {
            //通过连接池获得连接
            jedis = jedisPool.getResource();
            //设置时间
            jedis.set("password","123");
            //获取数据
            String password = jedis.get("password");
            //打印数据
            System.out.println(password);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if (jedis!=null){
                jedis.close();
            }
            if (jedisPool!=null){
                jedisPool.close();
            }
        }
    }


}
