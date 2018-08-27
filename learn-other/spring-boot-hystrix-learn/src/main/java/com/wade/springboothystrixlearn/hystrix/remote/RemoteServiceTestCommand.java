package com.wade.springboothystrixlearn.hystrix.remote;

import com.netflix.hystrix.HystrixCommand;

/**
 * @Author :lwy
 * @Date : 2018/8/24 18:34
 * @Description :
 */
public class RemoteServiceTestCommand extends HystrixCommand<String> {

    private RemoteServiceTestSimulator serviceTestSimulator;

    public RemoteServiceTestCommand(Setter config, RemoteServiceTestSimulator serviceTestSimulator) {
        super(config);
        this.serviceTestSimulator = serviceTestSimulator;
    }

    @Override
    protected String run() throws Exception {
        return serviceTestSimulator.execute();
    }

    /*@Override
    protected String getFallback() {
        return "this is fallback....";
    }*/
}
