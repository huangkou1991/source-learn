package com.gradle.test.base;

import com.gradle.test.BaseTest;
import com.wade.gradle.learn.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author :lwy
 * @date 2018/6/7 11:49
 */
public class OrderServiceTestCase extends BaseTest {


    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderService() {
        String result = orderService.getOrderById(9);
        System.err.println(result);
    }
}
