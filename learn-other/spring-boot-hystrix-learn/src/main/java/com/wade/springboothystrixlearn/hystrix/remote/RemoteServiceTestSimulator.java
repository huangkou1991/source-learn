package com.wade.springboothystrixlearn.hystrix.remote;

/**
 * @Author :lwy
 * @Date : 2018/8/24 18:32
 * @Description :模拟远端的服务
 */
public class RemoteServiceTestSimulator {

    private long wait;

    public RemoteServiceTestSimulator(long wait) {
        this.wait = wait;
    }

    public String execute() throws InterruptedException {
        Thread.sleep(wait);
        return "Success";
    }
}
