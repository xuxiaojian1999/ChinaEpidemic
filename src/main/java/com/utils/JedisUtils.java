package com.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtils {
    @Autowired
    JedisPool jedisPool;
    private Logger logger= LoggerFactory.getLogger(JedisUtils.class);
    //获取jedis
    private Jedis getJedis(){
        return jedisPool.getResource();
    }

    //关闭jedis
    private void cloneJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}