package com.dao;

import com.pojo.Broadcast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BroadcastMapper {
    List<Broadcast> selectAllBroadcast();
    Integer updateReleaseTimeById(@Param("id") Integer id,@Param("modifier") String modifier);
    Integer updateBroadcast(@Param("id") Integer id,@Param("digest") String digest,@Param("source") String source,
                            @Param("title") String title,@Param("releaseTime") String releaseTime,@Param("modifier") String modifier);
    Integer insertBroadcast(@Param("digest") String digest,@Param("source") String source,@Param("title") String title,
                            @Param("releaseTime") String releaseTime,@Param("founder") String founder);
}
