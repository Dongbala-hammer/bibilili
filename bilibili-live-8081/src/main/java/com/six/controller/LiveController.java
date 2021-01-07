package com.six.controller;


import com.six.pojo.resp.ResultResp;
import com.six.pojo.vo.RoomMessage;
import com.six.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ZhouJinDong
 * @Date 2021/1/4
 */

@RestController
@RequestMapping("/live")
public class LiveController {

    @Autowired
    LiveService liveService;

    @RequestMapping("/openLive")
    public ResultResp openLive(@RequestBody RoomMessage roomMessage, HttpServletRequest request){
        return  liveService.openLive(roomMessage,request);
    }

    @RequestMapping("/findRoomByUserId")
    public ResultResp findRoomByUserId( HttpServletRequest request){
        return  liveService.findRoomByUserId(request);
    }

    @RequestMapping("/findByLimit/{page}/{size}")
    public ResultResp findByLimit(@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        return  liveService.findByLimit(page,size);
    }

    @RequestMapping("/findRoomById")
    public ResultResp findRoomById(@RequestBody Map map){
        return  liveService.findRoomById((Integer) map.get("id"));
    }



}
