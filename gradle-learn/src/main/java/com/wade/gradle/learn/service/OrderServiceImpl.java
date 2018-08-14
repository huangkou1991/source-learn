package com.wade.gradle.learn.service;

/**
 * @author :lwy
 * @date 2018/6/7 11:48
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrderById(long id) {
        return "the order id is : " + id;
    }
}
