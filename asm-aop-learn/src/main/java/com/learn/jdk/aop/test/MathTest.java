package com.learn.jdk.aop.test;

import com.learn.jdk.aop.IMath;
import com.learn.jdk.aop.impl.IMathImpl;
import com.learn.jdk.aop.proxy.DynamicProxy;

/**
 * @author :lwy
 * @date 2018/7/6 17:43
 */
public class MathTest {


    public static void main(String[] args) {

        IMath iMath= (IMath) new DynamicProxy().getProxyTarget(new IMathImpl());
        int result=iMath.add(1,3);
        System.out.println(result);
    }
}
