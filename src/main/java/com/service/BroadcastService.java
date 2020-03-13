package com.service;

import com.pojo.Broadcast;

import java.util.List;

public interface BroadcastService {
    List<Broadcast> findAllBroadcast();
    boolean deleteBroadcast(Integer id,String modifier);
    boolean modifyBroadcast(Integer id,String digest,String source,
                            String title,String releaseTime,String modifier);
    boolean addBroadcast(String digest,String source,String title,String releaseTime,String founder);
}
