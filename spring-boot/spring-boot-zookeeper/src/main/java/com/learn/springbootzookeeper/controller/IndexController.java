package com.learn.springbootzookeeper.controller;

import com.learn.springbootzookeeper.cache.ServerCache;
import com.learn.springbootzookeeper.resp.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author :lwy
 * @Date : 2018/8/29 12:48
 * @Description :
 */
@Controller
@RequestMapping("/")
public class IndexController {


    @Autowired
    private ServerCache serverCache;

    /**
     * 获取所有路由节点
     *
     * @return
     */
    //@ApiOperation("获取所有路由节点")
    @RequestMapping(value = "getAllRoute", method = RequestMethod.POST)
    @ResponseBody()
    public BaseResponse<List<String>> getAllRoute() {
        BaseResponse<List<String>> res = new BaseResponse();
        List<String> all = serverCache.getAll();
        res.setDataBody(all);
        res.setCode("200");
        res.setMessage("success");
        return res;
    }

    /**
     * 获取所有路由节点
     *
     * @return
     */
    //@ApiOperation("获取所有路由节点")
    @RequestMapping(value = "getOneOfRoute", method = RequestMethod.POST)
    @ResponseBody()
    public BaseResponse<String> getOneOfRoute() {
        BaseResponse<String> res = new BaseResponse();
        String server = serverCache.selectServer();
        res.setDataBody(server);
        res.setCode("200");
        res.setMessage("success");
        return res;
    }
}