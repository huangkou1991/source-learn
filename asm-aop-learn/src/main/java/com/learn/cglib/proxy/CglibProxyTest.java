package com.learn.cglib.proxy;

import com.learn.jdk.aop.IMath;
import com.learn.jdk.aop.impl.IMathImpl;
import com.learn.jdk.aop.test.MathTest;

/**
 * @author :lwy
 * @date 2018/7/6 19:10
 * https://www.cnblogs.com/best/p/5679656.html
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        IMath iMath = (IMath) new DynamicProxy().getProxyObject(new IMathImpl());

        int result = iMath.add(1, 4);
        System.out.println(result);


        MathTest mathTest = (MathTest) new DynamicProxy().getProxyObject(new MathTest());

        System.out.println(mathTest);
    }
}
