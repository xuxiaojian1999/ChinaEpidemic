package com.controller;

import com.pojo.Broadcast;
import com.service.BroadcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/broadcast")
public class BroadcastController {
    @Autowired
    private BroadcastService broadcastService;
    private Logger logger= LoggerFactory.getLogger(BroadcastController.class);

    @RequestMapping("/getList")
    public List<Broadcast> getList(){
//        查询全部broadcast
        return broadcastService.findAllBroadcast();
    }

    @RequestMapping("/deleteBroadcast")
    public boolean deleteBroadcast(@RequestBody Broadcast broadcast){
        if (broadcast!=null){
            Integer id =broadcast.getId();
            String modifier =broadcast.getModifier();
            return broadcastService.deleteBroadcast(id,modifier);
        }
        return false;
    }

    @RequestMapping("/modifyBroadcast")
    public boolean modifyBroadcast(@RequestBody Broadcast broadcast){
        if (broadcast!=null){
            Integer id =broadcast.getId();
            String digest =broadcast.getDigest();
            String source =broadcast.getSource();
            String title =broadcast.getTitle();
            String releaseTime=broadcast.getReleaseTime();
            String modifier=broadcast.getModifier();
            return broadcastService.modifyBroadcast(id,digest,source,title,releaseTime,modifier);
        }
        return false;
    }

    @RequestMapping("/addBroadcast")
    public boolean addBroadcast(@RequestBody Broadcast broadcast){
        if (broadcast!=null){
            String digest=broadcast.getDigest();
            String source=broadcast.getSource();
            String title=broadcast.getTitle();
            String releaseTime=broadcast.getReleaseTime();
            String founder=broadcast.getFounder();
            return broadcastService.addBroadcast(digest,source,title,releaseTime,founder);
        }
        return false;
    }
}
