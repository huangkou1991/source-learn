package com.learn.jdk.aop.impl;

import com.learn.jdk.aop.IMath;

/**
 * @author :lwy
 * @date 2018/7/6 17:33
 */
public class IMathImpl implements IMath {
    @Override
    public int add(int a, int b) {
        return a+b;
    }
}
