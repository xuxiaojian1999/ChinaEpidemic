package com.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dao.BroadcastMapper;
import com.pojo.Broadcast;
import com.service.BroadcastService;
import com.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("BroadcastService")
public class BroadcastServiceImpl implements BroadcastService {
//    链接mysql
    @Resource
    private BroadcastMapper broadcastMapper;
//    链接redis
    @Autowired
    private RedisUtils redisUtils;
    //默认redis中的key
    private String key="broadcast_data";
    @Override
    public List<Broadcast> findAllBroadcast() {
//        先链接redis，查看是否有broadcast_data
        List<Broadcast> list=new ArrayList<>();
        if (redisUtils.exists(key)){
//            存在
            String value=redisUtils.get(key);
//            将字符串转换位对象
            list= JSONArray.parseArray(value,Broadcast.class);
        }else {
//            不存在
//            先从mysql读取
            list=broadcastMapper.selectAllBroadcast();
            updateRedis(list);
        }
        return list;
    }
    //更新redis
    private void updateRedis(List<Broadcast> list){
        //先从mysql取出
        if (list==null){
            list=broadcastMapper.selectAllBroadcast();
        }else {
            //当list不为空
            //将list转换为json字符串
            String value= JSON.toJSONString(list);
            String result="";
            Integer i=0;
            while (result!="ok"&&i<5){
                //当存储到redis成功，或存储了5次都没成功就退出循环
                result=redisUtils.set(key,value);
                i++;
            }
        }
    }
    @Override
    public boolean deleteBroadcast(Integer id, String modifier) {
        boolean flag=false;
        //先在mysql中删除
        //将releasetime改为0000,在selectAllBroadcast中就不会查询到
        if (broadcastMapper.updateReleaseTimeById(id,modifier)==1){
            //删除成功
            //更新新redis
            updateRedis(null);
            flag=true;
        }
        return flag;
    }
    @Override
    public boolean modifyBroadcast(Integer id, String digest, String source,
                                   String title, String releaseTime, String modifier) {
        boolean flag=false;
        //现在mysql中修改
        if (broadcastMapper.updateBroadcast(id,digest,source,title,releaseTime,modifier)==1){
            //修改成功
            //更新redis
            updateRedis(null);
            flag=true;
        }
        return flag;
    }
    @Override
    public boolean addBroadcast(String digest, String source, String title,
                                 String releaseTime, String founder) {
        boolean flag=false;
        if (broadcastMapper.insertBroadcast(digest,source,title,releaseTime,founder)==1){
            //新增成功
            //更新redis
            updateRedis(null);
            flag=true;
        }
        return flag;
    }
}
