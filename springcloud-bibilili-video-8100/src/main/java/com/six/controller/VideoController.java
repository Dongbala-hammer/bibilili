package com.six.controller;

import com.six.common.BaseRes;
import com.six.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * 按视频类别查询所有视频
     *
     * @param map page,limit,type（类别名称可为null,为null时查询所有）
     * @return
     */
    @PostMapping("/findAllVideo")
    public BaseRes findAllVideo(@RequestBody Map map) {
        return videoService.findAllVideo(map);
    }
<<<<<<< Updated upstream
/*    @PostMapping("/animationfindAll")
    public BaseRes animationfindAll(@RequestBody Map map) {
        return videoService.animationfindAll(map);
    }*/
}
=======

    /**
     * @param map id
     * @return
     */
    @PostMapping("/findVideoById")
    public BaseRes findVideoById(@RequestBody Map map){
        return videoService.findVideoById((Integer)map.get("id"));
    }
    /**
     * 轮播图的查询
     *
     * @return
     */
    @PostMapping("/extensionFind")
    public BaseRes extensionFind(@RequestBody Map map) {
        return videoService.extensionFind((Integer) map.get("num"));
    }

    /**
     * 按视频的标签查询视频
     *
     * @param map page,limit,tagId（标签id可为null，为null时查询所有）
     * @return
     */
    @PostMapping("/findVideoByTag")
    public BaseRes findVideoByTag(@RequestBody Map map) {
        return videoService.findVideoByTag(map);
    }

    @PostMapping("/findAllDesc")
    public BaseRes findAllDesc(@RequestBody Map map){
        return videoService.findAllDesc(map);
    }
}
>>>>>>> Stashed changes
