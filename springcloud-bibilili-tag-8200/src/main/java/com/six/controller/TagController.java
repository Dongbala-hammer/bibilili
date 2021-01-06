package com.six.controller;

import com.six.common.BaseRes;
import com.six.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/findVideoAllTag")
    public BaseRes findVideoAllTag(@RequestBody Map map) {
        return tagService.findVideoAllTag(map);
    }
}
