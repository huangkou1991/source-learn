package com.learn.serviceloader.impl;

import com.learn.serviceloader.IService;

/**
 * @author :lwy
 * @date 2018/7/12 17:50
 */
public class HelloIServiceImpl implements IService,Runnable {
    @Override
    public void execue() {
        System.out.println("hello");
    }

    @Override
    public void run() {
        System.out.println("hello,runnable");
    }
}
