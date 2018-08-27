package com.wade.springboothystrixlearn.controller;

import com.wade.springboothystrixlearn.aop.hystrix.service.SpringExistingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.AppletAudioClip;

/**
 * @Author :lwy
 * @Date : 2018/8/27 14:18
 * @Description :
 */
@RestController
public class HystrixController {

    @Autowired
    private SpringExistingClientService clientService;

    @RequestMapping("/withHystrix")
    public String withHystrix() throws InterruptedException{
        return clientService.invokeRemoteServiceWithHystrix();
    }

    @RequestMapping("/withOutHystrix")
    public String withOutHystrix() throws InterruptedException{
        return clientService.invokeRemoteServiceWithOutHystrix();
    }
}
