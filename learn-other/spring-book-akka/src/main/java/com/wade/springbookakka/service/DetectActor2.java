package com.wade.springbookakka.service;

import akka.actor.AbstractActor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/8/24 16:42
 * @Description :
 */
@Component("detectActor2")
@Scope("prototype")
public class DetectActor2 extends AbstractActor {

    @Override
    public Receive createReceive() {
        System.out.println("hello");
        return null;
    }
}
