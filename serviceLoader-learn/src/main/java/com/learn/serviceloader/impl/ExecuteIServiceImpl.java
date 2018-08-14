package com.learn.serviceloader.impl;

import com.learn.serviceloader.IService;

/**
 * @author :lwy
 * @date 2018/7/12 17:51
 */
public class ExecuteIServiceImpl implements IService,Runnable {
    @Override
    public void execue() {
        System.out.println("execute");
    }

    @Override
    public void run() {
        System.out.println("execute,Runnable");
    }
}
