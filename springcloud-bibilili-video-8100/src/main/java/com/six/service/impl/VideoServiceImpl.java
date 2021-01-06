package com.six.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.six.common.BaseRes;
import com.six.dao.VideoMapper;
import com.six.pojo.Tag;
import com.six.pojo.Video;
import com.six.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public BaseRes findAllVideo(Map map) {
        Integer page = (Integer) map.get("page");
        Integer limit = (Integer) map.get("limit");
        String typeName = null;
        if (map.get("type") != null) {
            typeName = map.get("type").toString();
        }
        PageHelper.startPage(page, limit);
        List<Video> videoList = videoMapper.findAll(typeName);
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        BaseRes baseRes = new BaseRes();
        baseRes.setCode(200);
        baseRes.setData(videoList);
        baseRes.setMsg("查询成功");
        baseRes.setTotal(pageInfo.getTotal());
        return baseRes;
    }

    @Override
    public BaseRes extensionFind() {
        List<Video> videoList = videoMapper.extensionFind();
        BaseRes baseRes = new BaseRes();
        baseRes.setCode(200);
        baseRes.setData(videoList);
        baseRes.setMsg("查询成功");
        return baseRes;
    }

}
