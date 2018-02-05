package com.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * function 测试jedis连接redis并执行部分操作
 * @author 十一城城主
 * @data 2018/1/18 13:18
 */
public class JedisDemo {

    /**
    　　* @Description: 创建单个Jedis对象操作Redis
    　　* @author 十一城城主
    　　* @date 2018/1/19 15:56 
    　　*/
    public static void singleJedisTest(){
        //设置IP地址以及端口
        Jedis jedis = new Jedis("39.106.173.68", 6379);
        //存入数据
        jedis.set("say","hello redis!");
        //获取数据
        String say = jedis.get("say");
        System.out.println(say);
        //释放资源
        jedis.close();
    }

    /**
    　　* @Description: 使用Jedis连接池操作Redis
    　　* @author 十一城城主
    　　* @date 2018/1/19 15:57
    　　*/
    public static void jedisPoolTest(){
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

    /**
    　　* @Description: 从redis中取五大类型的数据
    　　* @author 十一城城主
    　　* @date 2018/2/5 15:31
    　　*/
    public static void getTypesOfRedis(){
        //设置IP地址以及端口
        Jedis jedis = new Jedis("39.106.173.68", 6379);
        //存入数据
        Teacher teacher = new Teacher();
        teacher.setId("1");
        teacher.setName("Jack");
        teacher.setAge(18);
        List<String> strings = jedis.hmget("student", "username","age");
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
        //释放资源
        jedis.close();
    }

    /**
    　　* 主方法
    　　* @author 十一城城主
    　　* @date 2018/1/18 17:20
    　　*/
    public static void main(String[] args) {
        //调用单个jedis对象
        singleJedisTest();
        //从Jedis连接池中取Jedis对象
        jedisPoolTest();
        //从redis中取hash类型的数据
         getTypesOfRedis();
    }


}
