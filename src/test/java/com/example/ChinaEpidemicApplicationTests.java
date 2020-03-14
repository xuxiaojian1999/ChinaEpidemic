package com.example;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pojo.User;
import com.utils.JedisUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class ChinaEpidemicApplicationTests {
    @Autowired
    private JedisUtils jedisUtils;

    private Logger logger= LoggerFactory.getLogger(ChinaEpidemicApplicationTests.class);

    @Test
    void contextLoads() {
        User user =new User();
        user.setName("xiaoming");
        user.setAccount("123456");
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user);
        list.add(user);
        String json= JSONArray.toJSONString(list);
        logger.info(json);
        List<User> list2=new ArrayList<>();
        list2=JSONArray.parseArray(json,User.class);
        logger.info("-----for------");
        for (int i=0;i<2;i++){
            logger.info(list.get(i).getName());
        }
        User user2=JSONArray.parseObject(JSON.toJSONString(user),User.class);
        logger.info(user2.toString());
    }
    @Test
    void jedisResponse(){
        Jedis jedis=jedisUtils.getJedis();
        String  str=jedis.set("123","23" );
//        ok / 1
        logger.info(str);
    }
    @Test
    void timeTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(date));

    }
    @Test
    void timeOut(){
        int i=0;
        while (true){
            logger.info(""+i);
            if (i==5)break;
            i++;
        }
    }

}
