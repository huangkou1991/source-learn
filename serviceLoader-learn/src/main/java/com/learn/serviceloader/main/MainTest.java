package com.learn.serviceloader.main;

import com.learn.serviceloader.IService;

import java.util.ServiceLoader;

/**
 * @author :lwy
 * @date 2018/7/12 17:52
 */
public class MainTest {


    public static void main(String[] args) {

        ServiceLoader<IService> loader = ServiceLoader.load(IService.class);
        for (IService service : loader) {
            service.execue();
        }
    }
}
