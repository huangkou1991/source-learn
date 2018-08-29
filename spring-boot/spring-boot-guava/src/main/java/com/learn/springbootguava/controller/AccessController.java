package com.learn.springbootguava.controller;

import com.learn.springbootguava.service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author :lwy
 * @Date : 2018/8/29 19:01
 * @Description :
 */
@RestController
public class AccessController {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/access")
    @ResponseBody
    public String access() {
        //尝试获取令牌
        if (accessLimitService.tryAcquire()) {
            //模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("aceess success [" + sdf.format(new Date()) + "]");
            return "aceess success [" + sdf.format(new Date()) + "]";
        } else {
            System.err.println("aceess limit [" + sdf.format(new Date()) + "]");
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }
}
