package com.gradle.test.base;

import com.gradle.test.BaseTest;
import com.wade.gradle.learn.service.Hello;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author :lwy
 * @date 2018/6/7 11:45
 */
public class HelloServiceTestCase extends BaseTest {


    @Resource
    private Hello hello;


    @Test
    public void testHelloBean() {
        Assert.assertNotNull(hello);
    }
}
