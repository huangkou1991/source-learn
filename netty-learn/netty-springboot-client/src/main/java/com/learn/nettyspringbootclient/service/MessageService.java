package com.learn.nettyspringbootclient.service;

import com.learn.nettyspringbootclient.client.HeartBeatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/28 14:44
 * @Description :
 */
@Service
public class MessageService {

    @Autowired
    private HeartBeatClient heartBeatClient;


    public void sendMsg(){
        heartBeatClient.sendMsg("hello");
    }
}
