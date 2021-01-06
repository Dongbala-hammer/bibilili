package com.six.service.impl;

import com.six.common.BaseRes;
import com.six.dao.TagMapper;
import com.six.pojo.Tag;
import com.six.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public BaseRes findVideoAllTag(Map map) {
        String typeName = null;
        if (map.get("type") != null) {
            typeName = map.get("type").toString();
        }
        List<Tag> tagList = tagMapper.findVideoAllTag(typeName);
        BaseRes baseRes = new BaseRes();
        baseRes.setCode(200);
        baseRes.setMsg("查询成功");
        baseRes.setData(tagList);
        return baseRes;
    }
}
