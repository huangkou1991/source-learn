package com.learn.springbootlearn.controller;

import com.learn.springbootlearn.aop.MethodAnnotation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :lwy
 * @date 2018/7/31 18:22
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    @ResponseBody
    @MethodAnnotation
    public String hello() {
        return "hello";
    }


    @PostMapping(value = "/post",produces="application/json;charset=utf-8;")
    public String post(HttpServletRequest request, @RequestBody String params) throws JSONException {

        System.out.println(params);
        return params;
    }
}
