package com.six.client;

import com.six.common.BaseRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "bibilili-user")
public interface UserClient {
    @RequestMapping(value = "/user/findByUserId", method = RequestMethod.POST)
    public BaseRes findByUserId(@RequestBody Map map);
}
