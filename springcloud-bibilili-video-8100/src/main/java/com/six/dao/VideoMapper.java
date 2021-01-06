package com.six.dao;

import com.six.pojo.Tag;
import com.six.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> findAll(@Param("typeName") String typeName);

    List<Video> extensionFind();

}
