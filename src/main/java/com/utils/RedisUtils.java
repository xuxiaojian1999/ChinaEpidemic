package com.utils;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisUtils {
    @Autowired
    private JedisUtils jedisUtils;
    //查询是否存在,存在返回true
    public boolean exists(String key){
        Jedis jedis= jedisUtils.getJedis();
        boolean flag=jedis.exists(key);
        jedisUtils.cloneJedis(jedis);
        return flag;
    }
    //存储到redis，成功返回ok
    public String set(String key,String value){
        Jedis jedis=jedisUtils.getJedis();
        String flag=jedis.set(key,value);
        jedisUtils.cloneJedis(jedis);
        return flag;
    }
    //从redis获取,返回字符串
    public String get(String key){
        Jedis jedis=jedisUtils.getJedis();
        String value =jedis.get(key);
        jedisUtils.cloneJedis(jedis);
        return value;
    }
}
