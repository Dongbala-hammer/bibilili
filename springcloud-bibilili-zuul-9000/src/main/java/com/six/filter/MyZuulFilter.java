package com.six.filter;


import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.six.pojo.resp.ResultResp;
import com.six.utils.JwtUtils;
import com.six.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class MyZuulFilter extends ZuulFilter {

    private static final List URL_LIST = new ArrayList();

    @Autowired
    MyUtils myUtils;

    public MyZuulFilter(){
        URL_LIST.add("/bilibili-live/live/openve");


    }

    //拦截器何时进行拦截
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //拦截器的顺序
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    //对于那些请求进行拦截
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println("requestURL = " + requestURI);
        String[] split = requestURI.split("/");
        for (String s : split) {
            if (s.equals("findByLimit")){
                return false;
            }
        }
        if (URL_LIST.contains(requestURI)){
            return false;//放行
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截处理-------");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        ResultResp resultResp = new ResultResp();
        Cookie[] cookies = request.getCookies();

        String token = myUtils.getToken(cookies);
        if (token == null || cookies.length == 0){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            resultResp.setCode(303);
            resultResp.setMessage("请登录！！");
            Object o2 = JSONObject.toJSON(resultResp);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.print(o2);
            currentContext.setSendZuulResponse(false);
            return null;
        }
        JwtUtils jwtUtils = new JwtUtils();
        Map verify = jwtUtils.Verify(token);
        if (verify == null || verify.get("username") == null){
            response.setContentType("application/json;charset=utf-8");
            resultResp.setCode(302);
            resultResp.setMessage("登录信息过期，请重新登录！！");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.print(JSONObject.toJSON(resultResp));
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
