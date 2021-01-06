package com.six.dao;

import com.six.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> findVideoAllTag(String typeName);
}
