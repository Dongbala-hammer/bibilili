package com.six.controller;

import com.six.common.BaseRes;
import com.six.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/findAllVideo")
    public BaseRes findAllVideo(@RequestBody Map map) {
        return videoService.findAllVideo(map);
    }

    @PostMapping("/extensionFind")
    public BaseRes extensionFind() {
        return videoService.extensionFind();
    }
}