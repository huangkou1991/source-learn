package com.learn.springbootguava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Author :lwy
 * @Date : 2018/9/3 10:34
 * @Description :
 */
@Service
public class AsyncClient {

    @Autowired
    private AsyncService asyncService;

    public void async(){
        ListenableFuture<String> listenableFuture=asyncService.asyncNotify("id");
        //设置异步回调的返回内容
        asyncService.addCallback(listenableFuture,"消息通知成功", "消息通知失败");
    }
}
